package by.bsuir.blog.action.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.exception.PostServiceException;
import by.bsuir.blog.service.impl.PostServiceImpl;

public class DefaultAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(DefaultAction.class);

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (DefaultAction.class) {
                if (instance == null) {
                    instance = new DefaultAction();
                }
            }
        }
        return instance;
    }

    private final PostService postService;

    private DefaultAction() {
        this.postService = PostServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Post> posts = null;
        try {
            posts = this.postService.getAll();
        } catch (PostServiceException e) {
            LOGGER.error("Can't get posts",e);
        }
        request.setAttribute("posts", posts);
        return "/WEB-INF/pages/main.jsp";
    }

}
