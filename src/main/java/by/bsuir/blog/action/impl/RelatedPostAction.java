package by.bsuir.blog.action.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.impl.PostServiceImpl;

public class RelatedPostAction
        implements Action {

    private static final Logger LOGGER = LogManager.getLogger(RelatedPostAction.class);

    private static final String CATEGORY_SLUG = "category";
    private static final String TAG_TITLE = "tag";

    private static Action instance;

    public static Action getInstance() {
        if (instance == null) {
            synchronized (RelatedPostAction.class) {
                if (instance == null) {
                    instance = new RelatedPostAction();
                }
            }
        }
        return instance;
    }

    private final PostService postService;

    private RelatedPostAction() {
        this.postService = PostServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String categoryTitle = (String) request.getParameter(CATEGORY_SLUG);
        String tagTitle = (String) request.getParameter(TAG_TITLE);

        List<Post> posts = null;
        try {
            if (categoryTitle == null || categoryTitle.length() == 0) {
                posts = this.postService.tagPost(tagTitle);
            } else if (tagTitle == null || tagTitle.length() == 0) {
                posts = this.postService.categoryPost(categoryTitle);
            } else {
                return "/WEB-INF/pages/main.jsp";
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        request.setAttribute("posts", posts);
        return "/WEB-INF/pages/main.jsp";
    }

}
