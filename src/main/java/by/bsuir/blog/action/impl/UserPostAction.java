package by.bsuir.blog.action.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger(UserPostAction.class);

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

    private final PostService postService;
    private final UserService userService;

    private UserPostAction() {
        this.postService = PostServiceImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(LOGIN_PARAM);

        String userLogin = (String) request.getParameter(USER_LOGIN);

        Optional<User> user = null;
        List<Post> posts = null;

        try {
            user = this.userService.getByLogin(userLogin);
            if (!user.isPresent())
                return "/WEB-INF/pages/main.jsp";

            if (user.get().getLogin().equals(login))
                posts = this.postService.getUserPost(login);
            else
                posts = this.postService.getPublishedUserPost(userLogin);
        } catch (ValidationException e) {
            return "/WEB-INF/pages/main.jsp";
        } catch (UserServiceException | PostServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute("user", user.get());
        request.setAttribute("posts", posts);
        return "/WEB-INF/pages/userPost.jsp";
    }

}
