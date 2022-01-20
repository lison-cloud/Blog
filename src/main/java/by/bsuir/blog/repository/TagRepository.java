package by.bsuir.blog.repository;

import java.util.List;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.repository.exception.TagRepositoryException;

public interface TagRepository
        extends BaseRepository<Tag> {
        
    List<Tag> getAllByPostId(Object postId) throws TagRepositoryException;

    void addTagToPost(Object tagId, Object postId) throws TagRepositoryException;

    void removeTagFromPost(Object tagId, Object postId) throws TagRepositoryException;

}
