package by.bsuir.blog.repository;

import java.util.Optional;

import by.bsuir.blog.dto.UserRole;
import by.bsuir.blog.repository.exception.UserRoleRepositoryException;

public interface UserRoleRepository
    extends BaseRepository<UserRole> {
        
    Optional<UserRole> getByRole(String role) throws UserRoleRepositoryException;

}
