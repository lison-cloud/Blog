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

public class LoginAction
        implements Action {

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWD_PARAM = "password";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (LoginAction.class) {
                if (instance == null) {
                    instance = new LoginAction();
                }
            }
        }
        return instance;
    }

    private final UserService userService;

    private LoginAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session.getAttribute(EMAIL_PARAM) != null) {
            return "/blog";
        }

        String email = (String) request.getParameter(EMAIL_PARAM);
        String passwd = (String) request.getParameter(PASSWD_PARAM);

        if (email == null || passwd == null || email.length() == 0 || passwd.length() == 0) {
            System.out.println("0");
            return "/WEB-INF/pages/login.jsp";
        }

        User user = this.authenticate(email, passwd);
        if (user == null) {
            request.setAttribute("login_failure", true);
            return "/WEB-INF/pages/login.jsp";
        }

        session.setAttribute("login", user.getLogin());
        return "/blog";
    }

    private User authenticate(String login, String passwd) {
        User user = null;
        try {
            user = this.userService.authenticate(login, passwd);
        } catch (UserServiceException | ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

}
