package by.bsuir.blog.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import by.bsuir.blog.dto.PostComment;
import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.repository.PostCommentRepository;
import by.bsuir.blog.repository.PostRepository;
import by.bsuir.blog.repository.exception.PostCommentRepositoryException;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.impl.PostCommentRepositoryImpl;
import by.bsuir.blog.repository.impl.PostRepositoryImpl;
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

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    private PostCommentServiceImpl() {
        this.postRepository = PostRepositoryImpl.getInstance();
        this.postCommentRepository = PostCommentRepositoryImpl.getInstance();
    }

    @Override
    public List<PostComment> getPostComment(long postId) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isZeroOrLess(postId);
        try {
            return this.postCommentRepository.getByPostId(postId).stream()
                    .map(this::convertToPostComment)
                    .collect(Collectors.toList());
        } catch (PostCommentRepositoryException e) {
            throw new PostCommentServiceException(e);
        }
    }

    @Override
    public List<PostComment> getPostUserComment(long postId, String login)
            throws ValidationException, PostCommentServiceException {
        ValidationUtil.isZeroOrLess(postId);
        ValidationUtil.isPresented(login);

        try {
            return this.postCommentRepository.getByPostIdAndUserLogin(postId, login).stream()
                    .map(this::convertToPostComment).collect(Collectors.toList());
        } catch (PostCommentRepositoryException e) {
            throw new PostCommentServiceException(e);
        }
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
        this.save0(entity);
    }

    @Override
    public void save(PostComment comment, String postSlug) throws ValidationException, PostCommentServiceException {
        ValidationUtil.isPresented(comment);
        ValidationUtil.isZeroLength(postSlug);

        PostCommentEntity entity = this.convertPostCommentEntity(comment);
        try {
            entity.setPostId(
                    this.postRepository.getBySlug(postSlug)
                            .orElseThrow(PostCommentServiceException::new).getId());
            this.save0(entity);
        } catch (RepositoryException e) {
            throw new PostCommentServiceException(e);
        }
    }

    private void save0(PostCommentEntity entity) throws PostCommentServiceException {
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
