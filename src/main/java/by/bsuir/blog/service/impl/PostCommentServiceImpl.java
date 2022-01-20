package by.bsuir.blog.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.blog.dto.PostComment;
import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.repository.PostCommentRepository;
import by.bsuir.blog.repository.exception.PostCommentRepositoryException;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.impl.PostCommentRepositoryImpl;
import by.bsuir.blog.service.PostCommentService;
import by.bsuir.blog.service.exception.PostCommentServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.ValidationUtil;

public class PostCommentServiceImpl
        implements PostCommentService {

    private static PostCommentService instance;

    public static PostCommentService getInstance() {
        if (instance == null) {
            synchronized (PostCommentServiceImpl.class) {
                if (instance == null) {
                    instance = new PostCommentServiceImpl();
                }
            }
        }
        return instance;
    }

    private final PostCommentRepository postCommentRepository;

    private PostCommentServiceImpl() {
        this.postCommentRepository = PostCommentRepositoryImpl.getInstance();
    }

    @Override
    public List<PostComment> postComment(long postId) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isZeroOrLess(postId);
        List<PostComment> comments = new ArrayList<>();
        try {
            this.postCommentRepository.findByPostId(postId).forEach(
                    e -> comments.add(this.convertToPostComment(e)));
        } catch (PostCommentRepositoryException e) {
            throw new PostCommentServiceException(e);
        }
        return comments;
    }

    @Override
    public List<PostComment> findByPostAndUser(long postId, String login)
            throws ValidationException, PostCommentServiceException {
        ValidationUtil.isZeroOrLess(postId);
        ValidationUtil.isPresented(login);

        List<PostComment> comments = new ArrayList<>();
        try {
            this.postCommentRepository.findByPostIdAndUserLogin(postId, login).forEach(
                    e -> comments.add(this.convertToPostComment(e)));
        } catch (PostCommentRepositoryException e) {
            throw new PostCommentServiceException(e);
        }
        return comments;
    }

    private PostComment convertToPostComment(PostCommentEntity entity) {
        PostComment comment = new PostComment();
        comment.setId(entity.getId());
        comment.setText(entity.getText());
        comment.setPublishedAt(entity.getPublishedAt());
        comment.setUserLogin(entity.getUserLogin());
        return comment;
    }

    private PostCommentEntity convertPostCommentEntity(PostComment comment) {
        PostCommentEntity entity = new PostCommentEntity();
        entity.setId(comment.getId());
        entity.setUserLogin(comment.getUserLogin());
        entity.setText(comment.getText());
        return entity;
    }

    @Override
    public void save(PostComment comment, long postId) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isPresented(comment);
        ValidationUtil.isZeroOrLess(postId);

        PostCommentEntity entity = this.convertPostCommentEntity(comment);
        entity.setPostId(postId);
        entity.setPublishedAt(
                new Timestamp(Instant.now().toEpochMilli()));
        try {
            this.postCommentRepository.add(entity);
        } catch (RepositoryException e) {
            throw new PostCommentServiceException(e);
        }
    }

    @Override
    public void update(PostComment comment) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isPresented(comment);

        PostCommentEntity entity = this.convertPostCommentEntity(comment);
        entity.setPublishedAt(comment.getPublishedAt());

        try {
            this.postCommentRepository.update(entity);
        } catch (RepositoryException e) {
            throw new PostCommentServiceException(e);
        }
    }

    @Override
    public void delete(long commentId) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isZeroOrLess(commentId);
        try {
            this.postCommentRepository.removeById(commentId);
        } catch (RepositoryException e) {
            throw new PostCommentServiceException(e);
        }
    }

}
