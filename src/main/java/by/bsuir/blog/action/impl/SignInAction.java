package by.bsuir.blog.action.impl;

import java.util.Optional;

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

        String email = (String) request.getParameter(EMAIL_PARAM);

        if(email==null) {
            return "/WEB-INF/pages/signin.jsp";
        }

        String login = (String) request.getParameter(LOGIN_PARAM);
        String passwd = (String) request.getParameter(PASSWD_PARAM);
        
        Optional<User> user = null;
        try {
            user = this.userService.registrate(email, login, passwd);
        } catch (ValidationException e) {
            request.setAttribute("signin_failure", true);
            request.setAttribute("failure_message", "Invalid data");
            return "/WEB-INF/pages/signin.jsp";
        } catch (UserServiceException e) {
            LOGGER.error(e);
        }

        if(!user.isPresent()) {
            request.setAttribute("signin_failure", true);
            request.setAttribute("failure_message", "user with that email|login exists");
            return "/WEB-INF/pages/signin.jsp";
        }

        session.setAttribute(LOGIN_PARAM, user.get().getLogin());
        return "/blog";
    }
}
