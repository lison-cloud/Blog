package by.bsuir.blog.service.util.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.service.util.AuthenticationUtil;

public class AuthenticationUtilImpl
        implements AuthenticationUtil {

    private static final SecureRandom RANDOM;
    private static final int HASHING_ROUNDS = 10;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such algorithm", e);
        }
    }

    @Override
    public boolean authenticate(UserEntity entity, String passwd) {
        return BCrypt.checkpw(passwd, entity.getHashPassword());
    }

    @Override
    public String createNewPassword(String passwd) {
        return BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
    }

}
