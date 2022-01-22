package by.bsuir.blog.action.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class UserProfileAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(UserProfileAction.class);

    private static final String USER_LOGIN = "userLogin";

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

        Optional<User> user = null;
        try {
            user = this.userService.userByLogin(userLogin);
        } catch (ValidationException e) {
            return "/WEB-INF/pages/main.jsp";
        } catch (UserServiceException e) {
            LOGGER.error(e);
        }

        if (!user.isPresent())
            return "/WEB-INF/pages/main.jsp";

        request.setAttribute("user", user.get());
        return "/WEB-INF/pages/userProfile.jsp";
    }

}
