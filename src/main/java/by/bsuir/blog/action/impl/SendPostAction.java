package by.bsuir.blog.action.impl;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.Category;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.service.CategoryService;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.TagService;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.CategoryServiceImpl;
import by.bsuir.blog.service.impl.PostServiceImpl;
import by.bsuir.blog.service.impl.TagServiceImpl;

public class SendPostAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SendPostAction.class);

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (SendPostAction.class) {
                if (instance == null) {
                    instance = new SendPostAction();
                }
            }
        }
        return instance;
    }

    private final PostService postService;
    private final CategoryService categoryService;
    private final TagService tagService;

    private SendPostAction() {
        this.postService = PostServiceImpl.getInstance();
        this.categoryService = CategoryServiceImpl.getInstance();
        this.tagService = TagServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = (String) session.getAttribute(LOGIN_PARAM);

        if(login==null) {
            return "/blog";
        }

        String postTitle = (String) request.getParameter(POST_TITLE);
        String postBody = (String) request.getParameter(POST_BODY);
        String postCategory = (String) request.getParameter(POST_CATEGORY);
        String postType = (String) request.getParameter(POST_TYPE);
        String postTag = (String) request.getParameter(POST_TAG);
        String postSlug = (String) request.getParameter(POST_SLUG);

        if (postTag == null) {
            postTag = "";
        }

        Post post = new Post();
        post.setUserLogin(login);
        post.setTitle(postTitle);
        post.setContent(postBody);
        post.setSlug(postSlug);
        try {
            Optional<Category> cOptional = this.categoryService.getBySlug(postCategory);
            if (!cOptional.isPresent()) {
                request.setAttribute("write_faqilure", true);
                request.setAttribute("failure_message", "invalid category");
                return "/WEB-INF/pages/postWrite.jsp";
            }
            post.setCategory(cOptional.get());
            String[] tag = postTag.split("\\s");
            post.setTags(Arrays.asList(tag));

            if ("publish".equals(postType)) {
                post.setPublished(true);
            }
            
            this.postService.save(post);

        } catch (ValidationException e) {
            request.setAttribute("write_failure", true);
            request.setAttribute("failure_message", "invalid data");
            return "/WEB-INF/pages/postWrite.jsp";
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }

        return "/blog";
    }

}
