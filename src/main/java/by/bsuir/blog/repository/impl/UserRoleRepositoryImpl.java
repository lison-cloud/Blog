package by.bsuir.blog.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import by.bsuir.blog.dto.UserRole;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.UserRoleRepository;

public class UserRoleRepositoryImpl
        extends AbstractBaseRepository<UserRole>
        implements UserRoleRepository {

    private static Map<String, UserRole> roles = new HashMap<>();

    static {
        roles.put("admin", new UserRole(1, "admin"));
        roles.put("writer", new UserRole(2, "writer"));
        roles.put("user", new UserRole(3, "user"));
    }

    private static UserRoleRepository instance;

    private UserRoleRepositoryImpl() {
        super(UserRole.class);
    }

    public static UserRoleRepository getInstance() {
        if (instance == null) {
            synchronized (UserRoleRepositoryImpl.class) {
                if (instance == null) {
                    instance = new UserRoleRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<UserRole> getByRole(String role) {
        UserRole userRole = roles.get(role);
        return Optional.ofNullable(userRole);
    }

    @Override
    public Optional<UserRole> find(Object id) {
        for (String str : roles.keySet()) {
            if (id.equals(roles.get(str).getId()))
                return Optional.ofNullable(roles.get(str));
        }
        return Optional.empty();
    }

}
