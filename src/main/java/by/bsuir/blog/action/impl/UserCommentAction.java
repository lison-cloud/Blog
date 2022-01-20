package by.bsuir.blog.action.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.PostServiceException;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.PostServiceImpl;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class UserCommentAction
        implements Action {
    private static String USER_LOGIN = "userLogin";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (UserCommentAction.class) {
                if (instance == null) {
                    instance = new UserCommentAction();
                }
            }
        }
        return instance;
    }

    private final PostService postService;
    private final UserService userService;

    private UserCommentAction() {
            this.postService = PostServiceImpl.getInstance();
            this.userService = UserServiceImpl.getInstance();
        }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userLogin = (String) request.getParameter(USER_LOGIN);

        if (userLogin == null || userLogin.length() == 0) {
            return "/WEB-INF/pages/main.jsp";
        }
        List<Post> posts = null;
        User user = null;

        try {
            user = this.userService.userByLogin(userLogin);
            posts = this.postService.postWithUserComment(userLogin);
        } catch (UserServiceException | ValidationException | PostServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("user", user);
        request.setAttribute("posts", posts);
        return "/WEB-INF/pages/userComment.jsp";
    }
}
