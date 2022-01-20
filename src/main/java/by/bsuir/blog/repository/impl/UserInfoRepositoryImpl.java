package by.bsuir.blog.repository.impl;

import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.UserInfoRepository;

public class UserInfoRepositoryImpl
        extends AbstractBaseRepository<UserInfo>
        implements UserInfoRepository {

    private static UserInfoRepository instance;

    private UserInfoRepositoryImpl() {
        super(UserInfo.class);
    }

    public static UserInfoRepository getInstance() {
        if (instance == null) {
            synchronized (UserInfoRepositoryImpl.class) {
                if (instance == null) {
                    instance = new UserInfoRepositoryImpl();
                }
            }
        }
        return instance;
    }

}
