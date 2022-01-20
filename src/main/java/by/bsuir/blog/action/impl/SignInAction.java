package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class SignInAction
        implements Action {

    private static String EMAIL_PARAM = "email";
    private static String LOGIN_PARAM = "login";
    private static String PASSWD_PARAM = "password";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (SignInAction.class) {
                if (instance == null) {
                    instance = new SignInAction();
                }
            }
        }
        return instance;
    }

    private final UserService userService;

    private SignInAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session.getAttribute(LOGIN_PARAM) != null) {
            return "/blog";
        }

        String login = (String) request.getParameter(LOGIN_PARAM);
        String email = (String) request.getParameter(EMAIL_PARAM);
        String passwd = (String) request.getParameter(PASSWD_PARAM);

        if (login == null || passwd == null
                || login.length() == 0 || passwd.length() == 0) {
            return "/WEB-INF/pages/signin.jsp";
        }

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setUserRole("user");

        try {
            this.userService.registrate(user, passwd);
        } catch (UserServiceException | ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        session.setAttribute(LOGIN_PARAM, user.getLogin());

        return "/blog";
    }
}
