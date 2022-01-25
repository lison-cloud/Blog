package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsuir.blog.action.Action;

public class LogoutAction
        implements Action {

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (LoginAction.class) {
                if (instance == null) {
                    instance = new LogoutAction();
                }
            }
        }
        return instance;
    }

    private LogoutAction() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(LOGIN_PARAM) != null) {
            session.invalidate();
        }
        return "/blog";
    }
}
