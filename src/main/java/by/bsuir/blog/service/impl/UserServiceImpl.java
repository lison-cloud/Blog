package by.bsuir.blog.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.blog.dto.User;
import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.dto.UserRole;
import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.repository.UserRepository;
import by.bsuir.blog.repository.UserRoleRepository;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.exception.UserRepositoryException;
import by.bsuir.blog.repository.exception.UserRoleRepositoryException;
import by.bsuir.blog.repository.impl.UserRoleRepositoryImpl;
import by.bsuir.blog.repository.impl.UserRepositoryImpl;
import by.bsuir.blog.service.UserInfoService;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.exception.UserInfoServiceException;
import by.bsuir.blog.service.exception.UserServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.AuthenticationUtil;
import by.bsuir.blog.service.util.ValidationUtil;
import by.bsuir.blog.service.util.impl.AuthenticationUtilImpl;

public class UserServiceImpl
        implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    private final AuthenticationUtil authenticationUtil;

    private final UserInfoService userInfoService;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private UserServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
        this.userRoleRepository = UserRoleRepositoryImpl.getInstance();

        this.userInfoService = UserInfoServiceImpl.getInstance();

        this.authenticationUtil = new AuthenticationUtilImpl();
    }

    @Override
    public Optional<User> authenticate(String email, String passwd) throws ValidationException, UserServiceException {
        ValidationUtil.isValidEmail(email);
        ValidationUtil.isValidPassword(passwd);

        UserEntity entity = null;
        try {
            Optional<UserEntity> eOptional = this.userRepository.getByEmail(email);
            if (!eOptional.isPresent())
                return Optional.empty();
            entity = eOptional.get();
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }

        if (!this.authenticationUtil.authenticate(entity, passwd)) {
            LOGGER.warn("log failed for " + email);
            return Optional.empty();
        }

        return Optional.of(this.convertToUser(entity));
    }

    @Override
    public Optional<User> registrate(String email, String login, String passwd)
            throws ValidationException, UserServiceException {
        ValidationUtil.isValidEmail(email);
        ValidationUtil.isValidLogin(login);
        ValidationUtil.isValidPassword(passwd);

        User user = new User();

        user.setEmail(email);
        user.setLogin(login);
        user.setHashPassword(
                this.authenticationUtil.createNewPassword(passwd));
        user.setUserRole("user");

        UserInfo userInfo = new UserInfo();
        userInfo.setRegisteredAt(new Timestamp(Instant.now().toEpochMilli()));

        user.setUserInfo(userInfo);

        if (!this.save(user))
            return Optional.empty();

        return Optional.of(user);
    }

    @Override
    public Optional<User> userByLogin(String userLogin) throws ValidationException, UserServiceException {
        ValidationUtil.isValidLogin(userLogin);

        UserEntity entity = null;
        try {
            Optional<UserEntity> eOptional = this.userRepository.getByLogin(userLogin);
            if (!eOptional.isPresent())
                return Optional.empty();
            entity = eOptional.get();
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
        return Optional.of(this.convertToUser(entity));
    }

    private User convertToUser(UserEntity entity) throws UserServiceException {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setLogin(entity.getLogin());
        user.setHashPassword(entity.getHashPassword());

        UserInfo userInfo = null;
        try {
            userInfo = this.userInfoService.get(entity.getUserInfoId());
        } catch (UserInfoServiceException | ValidationException e) {
            throw new UserServiceException("userinfo for: " + user.getEmail(), e);
        }

        user.setUserInfo(userInfo);

        UserRole role = null;
        try {
            role = this.userRoleRepository.find(entity.getUserRoleId()).get();
        } catch (RepositoryException e) {
            new UserServiceException("user role for: " + user.getEmail(), e);
        }

        user.setUserRole(role.getRole());
        return user;
    }

    private UserEntity convertToUserEntity(User user) throws UserServiceException {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setLogin(user.getLogin());
        entity.setHashPassword(user.getHashPassword());
        try {
            entity.setUserRoleId(
                    this.userRoleRepository.getByRole(
                            user.getUserRole()).get().getId());
        } catch (UserRoleRepositoryException e) {
            throw new UserServiceException(e);
        }

        return entity;
    }

    @Override
    public boolean save(User user) throws ValidationException, UserServiceException {
        ValidationUtil.isPresented(user);

        if (!this.isUserEmailAndLoginUnique(user))
            return false;

        UserEntity entity = this.convertToUserEntity(user);
        try {
            entity.setUserInfoId(
                    this.userInfoService.save(user.getUserInfo()));
            this.userRepository.add(entity);
        } catch (UserInfoServiceException | RepositoryException e) {
            throw new UserServiceException(e);
        }
        return true;
    }

    private boolean isUserEmailAndLoginUnique(User user) throws UserServiceException {

        Optional<UserEntity> entity;
        try {
            entity = this.userRepository.getByEmail(user.getEmail());
            if (!entity.isPresent())
                entity = this.userRepository.getByLogin(user.getLogin());
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
        return !entity.isPresent();
    }

    @Override
    public void update(User user) throws ValidationException, UserServiceException {
        ValidationUtil.isPresented(user);

        UserEntity entity = this.convertToUserEntity(user);

        try {
            this.userInfoService.update(user.getUserInfo());
            this.userRepository.update(entity);
        } catch (UserInfoServiceException | RepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void delete(User user) throws ValidationException, UserServiceException {
        ValidationUtil.isPresented(user);

        try {
            this.userRepository.removeById(user.getId());
            this.userInfoService.delete(user.getUserInfo().getId());

        } catch (RepositoryException | UserInfoServiceException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public Optional<User> userByEmail(String userEmail) throws ValidationException, UserServiceException {
        ValidationUtil.isValidEmail(userEmail);

        UserEntity entity = null;
        try {
            Optional<UserEntity> eOptional = this.userRepository.getByEmail(userEmail);
            if(!eOptional.isPresent())
                return Optional.empty();
            entity = eOptional.get();
        }catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
        return Optional.of(this.convertToUser(entity));
    }

    @Override
    public void changePassword(User user, String newPasswd) throws ValidationException, UserServiceException {
        ValidationUtil.isPresented(user);
        ValidationUtil.isValidPassword(newPasswd);

        user.setHashPassword(this.authenticationUtil.createNewPassword(newPasswd));
        this.update(user);
    }

}
