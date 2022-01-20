package by.bsuir.blog.repository;

import java.util.Optional;

import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.repository.exception.UserRepositoryException;

public interface UserRepository
        extends BaseRepository<UserEntity> {

    Optional<UserEntity> getByEmail(String email) throws UserRepositoryException;

    Optional<UserEntity> getByLogin(String login) throws UserRepositoryException;

}
