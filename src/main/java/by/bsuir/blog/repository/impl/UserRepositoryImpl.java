package by.bsuir.blog.repository.impl;

import java.util.Optional;

import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.UserRepository;
import by.bsuir.blog.repository.exception.UserRepositoryException;

public class UserRepositoryImpl
                extends AbstractBaseRepository<UserEntity>
                implements UserRepository {

        private static UserRepository instance;

        private UserRepositoryImpl() {
                super(UserEntity.class);
        }

        public static UserRepository getInstance() {
                if (instance == null) {
                        synchronized (UserRepositoryImpl.class) {
                                if (instance == null) {
                                        instance = new UserRepositoryImpl();
                                }
                        }
                }
                return instance;
        }

        @Override
        public Optional<UserEntity> getByLogin(String login) throws UserRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<UserEntity> cs = cb.createSelect(this.table);

                UserEntity obj = null;
                try {
                        obj = this.entityManager.createQuery(
                                        cs.select(cs.where(cb.equal(table.getColumn("u_login"), login))))
                                        .getSingleResult();
                } catch (MapperException e) {
                        throw new UserRepositoryException(e);
                }
                return Optional.ofNullable(obj);
        }

        @Override
        public Optional<UserEntity> getByEmail(String email) throws UserRepositoryException {
                CriteriaBuilder cb = this.entityManager.criteriaBuilder();
                CriteriaSelect<UserEntity> cs = cb.createSelect(this.table);

                UserEntity obj = null;
                try {
                        obj = this.entityManager.createQuery(
                                        cs.select(cs.where(cb.equal(table.getColumn("u_email"), email))))
                                        .getSingleResult();
                } catch (MapperException e) {
                        throw new UserRepositoryException(e);
                }
                return Optional.ofNullable(obj);
        }

}
