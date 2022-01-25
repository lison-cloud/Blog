package by.bsuir.blog.action.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.exception.PostServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.PostServiceImpl;

public class PostDetailAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(PostDetailAction.class);

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (PostDetailAction.class) {
                if (instance == null) {
                    instance = new PostDetailAction();
                }
            }
        }
        return instance;
    }

    private final PostService postService;

    private PostDetailAction() {
        this.postService = PostServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String postSlug = (String) request.getParameter(SLUG_PARAM);

        Optional<Post> post = null;
        try {
            post = this.postService.getBySlug(postSlug);
        } catch (ValidationException e) {
            return "/WEB-INF/pages/main.jsp";
        } catch (PostServiceException e) {
            LOGGER.error(e);
        }

        if (!post.isPresent())
            return "/WEB-INF/pages/main.jsp";

        request.setAttribute("post", post.get());
        return "/WEB-INF/pages/postDetail.jsp";
    }

}
