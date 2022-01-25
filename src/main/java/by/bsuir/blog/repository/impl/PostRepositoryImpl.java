package by.bsuir.blog.repository.impl;

import java.util.List;
import java.util.Optional;

import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.mapper.util.QueryUtils;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.PostRepository;
import by.bsuir.blog.repository.exception.PostRepositoryException;

public class PostRepositoryImpl
                extends AbstractBaseRepository<PostEntity>
                implements PostRepository {

        private static PostRepository instance;

        private PostRepositoryImpl() {
                super(PostEntity.class);
        }

        public static PostRepository getInstance() {
                if (instance == null) {

                        synchronized (PostRepositoryImpl.class) {
                                if (instance == null) {
                                        instance = new PostRepositoryImpl();
                                }
                        }
                }
                return instance;
        }

        @Override
        public Optional<PostEntity> getByTitle(String title) throws PostRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<PostEntity> cs = cb.createSelect(this.table);

                try {
                        return Optional.ofNullable(this.entityManager.createQuery(
                                        cs.select(cs.where(cb.equal(table.getColumn("p_title"), title))))
                                        .getSingleResult());
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getByUserId(Object userId) throws PostRepositoryException {
                try {
                        return this.entityManager.<PostEntity>createQuery(
                                        "SELECT " + QueryUtils.cols(this.table) + " FROM post WHERE p_u_id = ?;",
                                        userId, this.table).getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getPublishedByUserId(Object userId) throws PostRepositoryException {
                try {
                        return this.entityManager.<PostEntity>createQuery(
                                        "SELECT " + QueryUtils.cols(this.table)
                                                        + " FROM post WHERE p_u_id = ? AND p_published=1;",
                                        userId, this.table).getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public Optional<PostEntity> getBySlug(String slug) throws PostRepositoryException {
                try {
                        return Optional.ofNullable(this.entityManager.<PostEntity>createQuery(
                                        "SELECT " + QueryUtils.cols(this.table) + " FROM post WHERE p_slug=?;", slug,
                                        this.table).getSingleResult());
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getByTag(String tag) throws PostRepositoryException {
                try {
                        return this.entityManager.<PostEntity>createQuery("SELECT " + QueryUtils.cols(this.table) +
                                        " FROM post JOIN post_tag ON post_tag.pt_p_id = post.p_id JOIN tag ON " +
                                        "post_tag.pt_t_id=tag.t_id WHERE tag.t_title=? AND post.p_published=1 ORDER BY post.p_publishedAt;",
                                        tag, this.table).getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getByCategorySlug(String categorySlug) throws PostRepositoryException {
                try {
                        return this.entityManager.<PostEntity>createQuery("SELECT " + QueryUtils.cols(this.table) +
                                        " FROM post JOIN category ON post.p_c_id = category.c_id WHERE category.c_slug = ?"
                                        + " AND post.p_published=1 ORDER BY post.p_publishedAt;",
                                        categorySlug, this.table).getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getPostWithUserComment(String login) throws PostRepositoryException {
                try {
                        return this.entityManager.<PostEntity>createQuery("SELECT " + QueryUtils.cols(this.table)
                                        + " FROM post JOIN post_comment ON post_comment.pc_p_id = post.p_id WHERE post_comment.pc_u_login=?",
                                        login, this.table).getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

        @Override
        public List<PostEntity> getLatestPost() throws PostRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<PostEntity> cs = cb.createSelect(this.table);

                try {
                        return this.entityManager.createQuery(
                                        cs.select(cs.orderBy(table.getColumn("p_publishedAt"), false)
                                                        .where(cb.equal(this.table.getColumn("p_published"), true))))
                                        .getResultList();
                } catch (MapperException e) {
                        throw new PostRepositoryException(e);
                }
        }

}
