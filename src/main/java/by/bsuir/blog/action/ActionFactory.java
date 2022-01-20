package by.bsuir.blog.action;

import javax.servlet.http.HttpServletRequest;

public interface ActionFactory {
    Action getAction(HttpServletRequest request);
}
