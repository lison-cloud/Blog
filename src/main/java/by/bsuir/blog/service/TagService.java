package by.bsuir.blog.service;

import java.util.List;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.service.exception.TagServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface TagService {
    List<Tag> getAll() throws TagServiceException;

    List<Tag> getPostTag(long postId) throws ValidationException, TagServiceException;

    void addTag(long postId, String tagTitle) throws ValidationException, TagServiceException;

    void save(Tag tag) throws ValidationException, TagServiceException;

    void update(Tag tag) throws ValidationException, TagServiceException;

    void delete(long tagId) throws ValidationException, TagServiceException;
}
