package by.bsuir.blog.service.util;

import by.bsuir.blog.entities.UserEntity;

public interface AuthenticationUtil {
    boolean authenticate(UserEntity entity, String passwd);

    String createNewPassword(String passwd);
}
