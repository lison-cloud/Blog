package by.bsuir.blog.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
            return this.tagRepository.getByPostId(postId);
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
    public List<Tag> addPostTag(long postId, List<String> tag) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroOrLess(postId);
        ValidationUtil.isPresented(tag);

        List<Tag> tags = new LinkedList<>();
        for (String t : tag) {
            try {
                Optional<Tag> oTag = this.tagRepository.getByTitle(t);
                if (!oTag.isPresent())
                    oTag = this.save(t);
                if (oTag.isPresent()) {
                    this.tagRepository.addPostTag(oTag.get().getId(), postId);
                    tags.add(oTag.get());
                }
            } catch (Exception e) {
                continue;
            }
        }
        return tags;
    }

    @Override
    public Optional<Tag> getByTitle(String title) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroLength(title);

        try {
            return this.tagRepository.getByTitle(title);
        } catch (TagRepositoryException e) {
            throw new TagServiceException(e);
        }
    }

    @Override
    public Optional<Tag> save(String title) throws ValidationException, TagServiceException {
        ValidationUtil.isZeroLength(title);

        Tag tag = new Tag();
        tag.setTitle(title);
        try {
            tag.setId(this.tagRepository.add(tag));
        } catch (RepositoryException e) {
            throw new TagServiceException(e);
        }
        return Optional.ofNullable(tag);
    }

}
