package by.bsuir.blog.service;

import java.util.List;

import by.bsuir.blog.dto.PostComment;
import by.bsuir.blog.service.exception.PostCommentServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface PostCommentService {

    List<PostComment> getPostComment(long postId) throws ValidationException, PostCommentServiceException;

    List<PostComment> getPostUserComment(long postId, String login)
            throws ValidationException, PostCommentServiceException;

    void save(PostComment postComment, long postId) throws ValidationException, PostCommentServiceException;

    void save(PostComment postComment, String postSlug) throws ValidationException, PostCommentServiceException;

    void update(PostComment postComment) throws ValidationException, PostCommentServiceException;

    void delete(long commentId) throws ValidationException, PostCommentServiceException;
}