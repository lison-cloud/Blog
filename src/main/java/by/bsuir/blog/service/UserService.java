package by.bsuir.blog.service;

import by.bsuir.blog.dto.User;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface UserService {

    User authenticate(String email, String passwd) throws ValidationException, UserServiceException;

    User userByEmail(String userEmail) throws ValidationException, UserServiceException;

    User userByLogin(String userLogin) throws ValidationException, UserServiceException;

    void save(User user) throws ValidationException, UserServiceException;

    void registrate(User user, String newPasswd) throws ValidationException, UserServiceException;

    void changePassword(User user, String newPasswd) throws ValidationException, UserServiceException;

    void update(User user) throws ValidationException, UserServiceException;

    void delete(User id) throws ValidationException, UserServiceException;
}
