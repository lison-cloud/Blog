package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;

public class WritePostAction
        implements Action {

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (WritePostAction.class) {
                if (instance == null) {
                    instance = new WritePostAction();
                }
            }
        }
        return instance;
    }

    private WritePostAction() {
        
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return "/WEB-INF/pages/postWrite.jsp";
    }

}
