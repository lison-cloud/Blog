package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class UserProfileAction
        implements Action {
    private static String USER_LOGIN = "userLogin";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (UserProfileAction.class) {
                if (instance == null) {
                    instance = new UserProfileAction();
                }
            }
        }
        return instance;
    }

    private final UserService userService;

    private UserProfileAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userLogin = (String) request.getParameter(USER_LOGIN);

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
        return "/WEB-INF/pages/userProfile.jsp";
    }

}
