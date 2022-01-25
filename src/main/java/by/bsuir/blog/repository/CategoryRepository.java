package by.bsuir.blog.repository;

import java.util.Optional;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.repository.exception.CategoryRepositoryException;

public interface CategoryRepository
    extends BaseRepository<Category> {
    
    Optional<Category> getBySlug(String slug) throws CategoryRepositoryException;
}
