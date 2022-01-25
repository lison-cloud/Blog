package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.PostComment;
import by.bsuir.blog.service.PostCommentService;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.PostCommentServiceImpl;

public class SendCommentAction
        implements Action {
    private static final Logger LOGGER = LogManager.getLogger(SendCommentAction.class);

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (SendPostAction.class) {
                if (instance == null) {
                    instance = new SendCommentAction();
                }
            }
        }
        return instance;
    }

    private final PostCommentService postCommentService;

    private SendCommentAction() {
        this.postCommentService = PostCommentServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = (String) session.getAttribute(LOGIN_PARAM);

        String postSlug = (String) request.getParameter(SLUG_PARAM);
        String commentContent = (String) request.getParameter(COMMENT_CONTENT);

        try {
            PostComment postComment = new PostComment();
            postComment.setText(commentContent);
            postComment.setUserLogin(login);

            this.postCommentService.save(postComment, postSlug);

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
