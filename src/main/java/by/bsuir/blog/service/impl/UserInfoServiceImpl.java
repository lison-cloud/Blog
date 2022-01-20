package by.bsuir.blog.service.impl;

import java.util.NoSuchElementException;

import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.repository.UserInfoRepository;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.impl.UserInfoRepositoryImpl;
import by.bsuir.blog.service.UserInfoService;
import by.bsuir.blog.service.exception.UserInfoServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.ValidationUtil;

public class UserInfoServiceImpl
        implements UserInfoService {

    private static UserInfoService instance;

    public static UserInfoService getInstance() {
        if (instance == null) {
            synchronized (UserInfoServiceImpl.class) {
                if (instance == null) {
                    instance = new UserInfoServiceImpl();
                }
            }
        }
        return instance;
    }

    private final UserInfoRepository userInfoRepository;

    private UserInfoServiceImpl() {
        this.userInfoRepository = UserInfoRepositoryImpl.getInstance();
    }

    @Override
    public UserInfo get(long id) throws ValidationException, UserInfoServiceException {
        ValidationUtil.isZeroOrLess(id);
        UserInfo info = null;
        try {
            info = this.userInfoRepository.find(id).get();
        } catch (RepositoryException | NoSuchElementException e) {
            throw new UserInfoServiceException(e);
        }
        return info;
    }

    @Override
    public long save(UserInfo userInfo) throws ValidationException, UserInfoServiceException {
        ValidationUtil.isPresented(userInfo);
        try {
            return this.userInfoRepository.add(userInfo);
        } catch (RepositoryException e) {
            throw new UserInfoServiceException(e);
        }
    }

    @Override
    public void update(UserInfo userInfo) throws ValidationException, UserInfoServiceException {
        ValidationUtil.isPresented(userInfo);
        try {
            this.userInfoRepository.update(userInfo);
        } catch (RepositoryException e) {
            throw new UserInfoServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ValidationException, UserInfoServiceException {
        ValidationUtil.isZeroOrLess(id);
        try {
            this.userInfoRepository.removeById(id);
        } catch (RepositoryException e) {
            throw new UserInfoServiceException(e);
        }
    }

}
