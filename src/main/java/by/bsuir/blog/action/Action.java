package by.bsuir.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    String EMAIL_PARAM = "email";
    String PASSWD_PARAM = "password";
    String LOGIN_PARAM = "login";
    String USER_LOGIN = "userLogin";
    String CATEGORY_SLUG = "category";
    String TAG_TITLE = "tag";
    String COMMENT_CONTENT = "comment-content";
    String POST_TITLE = "title";
    String POST_BODY = "content";
    String POST_CATEGORY = "category";
    String POST_TYPE = "type";
    String POST_TAG = "tag";
    String POST_SLUG = "slug";
    String SLUG_PARAM = "postSlug";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
