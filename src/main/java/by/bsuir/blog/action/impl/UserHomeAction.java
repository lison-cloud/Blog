package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class UserHomeAction
        implements Action {

    private static String USER_LOGIN = "login";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (UserHomeAction.class) {
                if (instance == null) {
                    instance = new UserHomeAction();
                }
            }
        }
        return instance;
    }

    private final UserService userService;

    private UserHomeAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userLogin = (String) request.getSession().getAttribute(USER_LOGIN);

        if (userLogin == null || userLogin.length() == 0) {
            return "/WEB-INF/pages/main.jsp";
        }

        User user = null;
        try {
            user = this.userService.userByLogin(userLogin);
        } catch (UserServiceException | ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.setAttribute("user", user);
        return "/WEB-INF/pages/userHome.jsp";
    }
}