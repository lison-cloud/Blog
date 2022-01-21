package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class SignInAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SignInAction.class);

    private static final String EMAIL_PARAM = "email";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWD_PARAM = "password";

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
        
        User user = null;
        try {
            this.userService.registrate(user, passwd);
        } catch (ValidationException e) {
            return "/WEB-INF/pages/signin.jsp";
        } catch (UserServiceException e) {
            LOGGER.error(e);
        }

        session.setAttribute(LOGIN_PARAM, login);
        return "/blog";
    }
}
