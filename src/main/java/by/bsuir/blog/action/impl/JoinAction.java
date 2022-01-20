package by.bsuir.blog.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;

public class JoinAction
        implements Action {

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (JoinAction.class) {
                if (instance == null) {
                    instance = new JoinAction();
                }
            }
        }
        return instance;
    }

    private JoinAction() {
        
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

}
