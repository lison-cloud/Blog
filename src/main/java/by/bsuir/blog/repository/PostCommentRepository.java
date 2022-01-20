package by.bsuir.blog.repository;

import java.util.List;

import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.repository.exception.PostCommentRepositoryException;

public interface PostCommentRepository
        extends BaseRepository<PostCommentEntity> {

    List<PostCommentEntity> findByUserLogin(String login) throws PostCommentRepositoryException;

    List<PostCommentEntity> findByPostId(Object postId) throws PostCommentRepositoryException;

    List<PostCommentEntity> findByPostIdAndUserLogin(Object postId, String login) throws PostCommentRepositoryException;

}
