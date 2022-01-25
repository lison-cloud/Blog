package by.bsuir.blog.repository.impl;

import java.util.Arrays;
import java.util.List;

import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.mapper.util.QueryUtils;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.PostCommentRepository;
import by.bsuir.blog.repository.exception.PostCommentRepositoryException;

public class PostCommentRepositoryImpl
                extends AbstractBaseRepository<PostCommentEntity>
                implements PostCommentRepository {

        private static PostCommentRepository instance;

        private PostCommentRepositoryImpl() {
                super(PostCommentEntity.class);
        }

        public static PostCommentRepository getInstance() {
                if (instance == null) {

                        synchronized (PostCommentRepositoryImpl.class) {
                                if (instance == null) {
                                        instance = new PostCommentRepositoryImpl();
                                }
                        }
                }
                return instance;
        }

        @Override
        public List<PostCommentEntity> getByUserLogin(String login) throws PostCommentRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<PostCommentEntity> cs = cb.createSelect(this.table);
                try {
                        return this.entityManager.createQuery(
                                        cs.select(cs.where(cb.equal(table.getColumn("pc_u_login"), login))))
                                        .getResultList();
                } catch (MapperException e) {
                        throw new PostCommentRepositoryException(e);
                }
        }

        @Override
        public List<PostCommentEntity> getByPostId(Object postId) throws PostCommentRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<PostCommentEntity> cs = cb.createSelect(this.table);

                try {
                        return this.entityManager.createQuery(
                                        cs.select(cs.where(cb.equal(table.getColumn("pc_p_id"), postId))))
                                        .getResultList();
                } catch (MapperException e) {
                        throw new PostCommentRepositoryException(e);
                }
        }

        @Override
        public List<PostCommentEntity> getByPostIdAndUserLogin(Object postId, String login)
                        throws PostCommentRepositoryException {
                List<Object> val = Arrays.asList(postId, login);

                try {
                        return this.entityManager
                                        .<PostCommentEntity>createQuery("SELECT " + QueryUtils.cols(this.table) +
                                                        " FROM post_comment WHERE pc_p_id=? AND pc_u_login=?;", val,
                                                        this.table)
                                        .getResultList();
                } catch (MapperException e) {
                        throw new PostCommentRepositoryException(e);
                }
        }

}
