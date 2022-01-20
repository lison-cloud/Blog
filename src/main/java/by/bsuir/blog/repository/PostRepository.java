package by.bsuir.blog.repository;

import java.util.List;
import java.util.Optional;

import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.repository.exception.PostRepositoryException;

public interface PostRepository
        extends BaseRepository<PostEntity> {

    Optional<PostEntity> getByTitle(String title) throws PostRepositoryException;

    Optional<PostEntity> getBySlug(String slug) throws PostRepositoryException;

    List<PostEntity> getAllOrderByPublishedDate(boolean asc) throws PostRepositoryException;

    List<PostEntity> getPostWithUserComment(String login) throws PostRepositoryException;

    List<PostEntity> getByTag(String tag) throws PostRepositoryException;

    List<PostEntity> getByUserId(Object userId) throws PostRepositoryException;

    List<PostEntity> getAllByCategorySlug(String categorySlug) throws PostRepositoryException;

}
