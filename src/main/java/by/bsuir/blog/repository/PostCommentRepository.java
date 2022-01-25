package by.bsuir.blog.repository;

import java.util.List;

import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.repository.exception.PostCommentRepositoryException;

public interface PostCommentRepository
        extends BaseRepository<PostCommentEntity> {

    List<PostCommentEntity> getByUserLogin(String login) throws PostCommentRepositoryException;

    List<PostCommentEntity> getByPostId(Object postId) throws PostCommentRepositoryException;

    List<PostCommentEntity> getByPostIdAndUserLogin(Object postId, String login) throws PostCommentRepositoryException;

}
