package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.impl.PostServiceImpl;

public class SendPostAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SendPostAction.class);

    private static final String POST_TITLE = "postTitle";
    private static final String POST_BODY = "postBody";

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

    private SendPostAction() {
        this.postService = PostServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String postTitle = (String) request.getParameter(POST_TITLE);
        String postBody = (String) request.getParameter(POST_BODY);

        if (postTitle == null || postBody == null || postTitle.length() == 0 || postBody.length() == 0) {
            return "/WEB-INF/pages/login.jsp";
        }

        return "/WEB-INF/pages/main.jsp";
    }

}
