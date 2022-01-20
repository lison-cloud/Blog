package by.bsuir.blog.service.impl;

import java.util.List;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.repository.TagRepository;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.exception.TagRepositoryException;
import by.bsuir.blog.repository.impl.TagRepositoryImpl;
import by.bsuir.blog.service.TagService;
import by.bsuir.blog.service.exception.TagServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.ValidationUtil;

public class TagServiceImpl
        implements TagService {

    private static TagService instance;

    public static TagService getInstance() {
        if (instance == null) {
            synchronized (TagServiceImpl.class) {
                if (instance == null) {
                    instance = new TagServiceImpl();
                }
            }
        }
        return instance;
    }

    private final TagRepository tagRepository;

    private TagServiceImpl() {
        this.tagRepository = TagRepositoryImpl.getInstance();
    }

    @Override
    public List<Tag> getAll() throws TagServiceException {
        try {
            return this.tagRepository.getAll();
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public List<Tag> getPostTag(long postId) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroOrLess(postId);
        try {
            return this.tagRepository.getAllByPostId(postId);
        } catch (TagRepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public void save(Tag tag) throws ValidationException, TagServiceException {
        ValidationUtil.isPresented(tag);
        try {
            this.tagRepository.add(tag);
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public void update(Tag tag) throws ValidationException, TagServiceException {
        ValidationUtil.isPresented(tag);
        try {
            this.tagRepository.update(tag);
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public void delete(long tagId) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroOrLess(tagId);
        try {
            this.tagRepository.removeById(tagId);
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public void addTag(long postId, String tagTitle) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroOrLess(postId);
        ValidationUtil.isZeroLength(tagTitle);

        Tag tag = new Tag();
        tag.setTitle(tagTitle);

        try {
            long tagId = this.tagRepository.add(tag);
            this.tagRepository.addTagToPost(tagId, postId);
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
    }

}
