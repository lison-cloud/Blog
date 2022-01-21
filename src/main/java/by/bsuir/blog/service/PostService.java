package by.bsuir.blog.service;

import java.util.List;

import by.bsuir.blog.dto.Post;
import by.bsuir.blog.service.exception.PostServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface PostService {

    List<Post> getAll() throws PostServiceException;

    List<Post> tagPost(String tag) throws ValidationException, PostServiceException;

    List<Post> categoryPost(String categoryTitle) throws ValidationException, PostServiceException;

    List<Post> postWithUserComment(String login) throws ValidationException, PostServiceException;

    List<Post> userPost(String login) throws ValidationException, PostServiceException;

    Post postBySlug(String slug) throws ValidationException, PostServiceException;

    void save(Post post) throws ValidationException, PostServiceException;

    void update(Post post) throws ValidationException, PostServiceException;

    void delete(long postId) throws ValidationException, PostServiceException;
}
