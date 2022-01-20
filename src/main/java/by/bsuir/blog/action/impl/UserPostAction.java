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

public class UserPostAction
        implements Action {

    private static String USER_LOGIN = "userLogin";

    private final PostService postService;
    private final UserService userService;

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (UserPostAction.class) {
                if (instance == null) {
                    instance = new UserPostAction();
                }
            }
        }
        return instance;
    }

    private UserPostAction() {
        this.postService = PostServiceImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userLogin = (String) request.getParameter(USER_LOGIN);

        if (userLogin == null || userLogin.length() == 0) {
            return "/WEB-INF/pages/main.jsp";
        }

        User user = null;
        List<Post> posts = null;

        try {
            user = this.userService.userByLogin(userLogin);
            posts = this.postService.userPost(userLogin);
        } catch (UserServiceException | ValidationException | PostServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.setAttribute("user", user);
        request.setAttribute("posts", posts);
        return "/WEB-INF/pages/userPost.jsp";
    }

}
