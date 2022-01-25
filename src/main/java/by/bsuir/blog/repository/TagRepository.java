package by.bsuir.blog.repository;

import java.util.List;
import java.util.Optional;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.repository.exception.TagRepositoryException;

public interface TagRepository
        extends BaseRepository<Tag> {

    List<Tag> getByPostId(Object postId) throws TagRepositoryException;

    long add(String title) throws TagRepositoryException;

    Optional<Tag> getByTitle(String title) throws TagRepositoryException;

    void addPostTag(Object tagId, Object postId) throws TagRepositoryException;

    void removePostTag(Object tagId, Object postId) throws TagRepositoryException;

}
