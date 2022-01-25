package by.bsuir.blog.service;

import java.util.Optional;

import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface UserService {

    Optional<User> authenticate(String email, String passwd) throws ValidationException, UserServiceException;

    Optional<User> getByEmail(String userEmail) throws ValidationException, UserServiceException;

    Optional<User> getByLogin(String userLogin) throws ValidationException, UserServiceException;

    boolean save(User user) throws ValidationException, UserServiceException;

    Optional<User> registrate(String email, String login, String passwd) throws ValidationException, UserServiceException;

    void changePassword(User user, String newPasswd) throws ValidationException, UserServiceException;

    void update(User user) throws ValidationException, UserServiceException;

    void delete(User id) throws ValidationException, UserServiceException;
}
