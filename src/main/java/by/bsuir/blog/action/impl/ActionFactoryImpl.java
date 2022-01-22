package by.bsuir.blog.action.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.action.ActionFactory;

public class ActionFactoryImpl
        implements ActionFactory {

    private final static String ACTION = "action";
    private final static String DEFAULT_ACTION = "";

    private static ActionFactory instance;

    public static ActionFactory getInstance() {
        if (instance == null) {
            synchronized (ActionFactoryImpl.class) {
                if (instance == null) {
                    instance = new ActionFactoryImpl();
                }
            }
        }
        return instance;
    }

    private final Map<String, Action> actions = new HashMap<>();

    private ActionFactoryImpl() {
        actions.put("", DefaultAction.getInstance());
        actions.put("latestpost", DefaultAction.getInstance());
        actions.put("login", LoginAction.getInstance());
        actions.put("logout", LogoutAction.getInstance());
        actions.put("signin", SignInAction.getInstance());
        actions.put("postdetail", PostDetailAction.getInstance());
        actions.put("userprofile", UserProfileAction.getInstance());
        actions.put("userpost", UserPostAction.getInstance());
        actions.put("usercomment", UserCommentAction.getInstance());
        actions.put("writepost", WritePostAction.getInstance());
        actions.put("sendpost", SendPostAction.getInstance());
        actions.put("relatedpost", RelatedPostAction.getInstance());
        actions.put("userhome", UserHomeAction.getInstance());
    }

    @Override
    public Action getAction(HttpServletRequest request) {
        String actParametr = request.getParameter(ACTION);
        if (actParametr == null)
            actParametr = DEFAULT_ACTION;
        return actions.get(actParametr.toLowerCase());
    }

}
