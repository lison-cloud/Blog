package by.bsuir.blog.service;

import java.util.List;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.service.exception.CategoryServiceException;
import by.bsuir.blog.service.exception.ValidationException;

public interface CategoryService {
    List<Category> getAll() throws ValidationException, CategoryServiceException;

    Category get(long categoryId) throws ValidationException, CategoryServiceException;

    void save(Category category) throws ValidationException, CategoryServiceException;

    void update(Category category) throws ValidationException, CategoryServiceException;

    void delete(long categoryId) throws ValidationException, CategoryServiceException;
}
