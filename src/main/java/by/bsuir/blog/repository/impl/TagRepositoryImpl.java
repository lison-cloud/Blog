package by.bsuir.blog.repository.impl;

import java.util.Arrays;
import java.util.List;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.mapper.util.QueryUtils;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.TagRepository;
import by.bsuir.blog.repository.exception.TagRepositoryException;

public class TagRepositoryImpl
        extends AbstractBaseRepository<Tag>
        implements TagRepository {

    private static TagRepository instance;

    private TagRepositoryImpl() {
        super(Tag.class);
    }

    public static TagRepository getInstance() {
        if (instance == null) {
            synchronized (TagRepositoryImpl.class) {
                if (instance == null) {
                    instance = new TagRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Tag> getAllByPostId(Object postId) throws TagRepositoryException {
        try {
            return this.entityManager.<Tag>createQuery(
                    "SELECT " + QueryUtils.cols(this.table)
                            + " FROM tag JOIN post_tag ON tag.t_id=post_tag.pt_t_id WHERE post_tag.pt_p_id=?",
                    postId, this.table).getResultList();
        } catch (MapperException e) {
            throw new TagRepositoryException();
        }
    }

    @Override
    public void addTagToPost(Object tagId, Object postId) throws TagRepositoryException {
        List<Object> vals = Arrays.asList(tagId, postId);
        try {
            this.entityManager.createQuery("INSERT INTO post_tag(pt_t_id, pt_p_id) VALUES (?,?)", vals);
        } catch (MapperException e) {
            throw new TagRepositoryException();
        }
    }

    @Override
    public void removeTagFromPost(Object tagId, Object postId) throws TagRepositoryException {
        List<Object> vals = Arrays.asList(tagId, postId);
        try {
            this.entityManager.createQuery("DELETE FROM post_tag WHERE pt_t_id=? AND pt_p_id=?", vals);
        } catch (MapperException e) {
            throw new TagRepositoryException();
        }
    }

}
