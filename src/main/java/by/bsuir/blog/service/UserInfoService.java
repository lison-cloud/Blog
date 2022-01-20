package by.bsuir.blog.service;

import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.service.exception.UserInfoServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface UserInfoService {
    UserInfo get(long id) throws ValidationException, UserInfoServiceException;

    long save(UserInfo userInfo) throws ValidationException, UserInfoServiceException;

    void update(UserInfo userInfo) throws ValidationException, UserInfoServiceException;

    void delete(long id) throws ValidationException, UserInfoServiceException;
}
