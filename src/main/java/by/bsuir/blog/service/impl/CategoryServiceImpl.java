package by.bsuir.blog.service.impl;

import java.util.List;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.repository.CategoryRepository;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.impl.CategoryRepositoryImpl;
import by.bsuir.blog.service.CategoryService;
import by.bsuir.blog.service.exception.CategoryServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.ValidationUtil;

public class CategoryServiceImpl
        implements CategoryService {

    private static CategoryService instance;

    public static CategoryService getInstance() {
        if (instance == null) {
            synchronized (CategoryServiceImpl.class) {
                if (instance == null) {
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

    private final CategoryRepository categoryRepository;

    private CategoryServiceImpl() {
        this.categoryRepository = CategoryRepositoryImpl.getInstance();
    }

    @Override
    public List<Category> getAll() throws CategoryServiceException {
        try {
            return this.categoryRepository.getAll();
        } catch (RepositoryException e) {
            throw new CategoryServiceException(e);
        }
    }

    @Override
    public void save(Category category) throws ValidationException, CategoryServiceException {
        ValidationUtil.isPresented(category);
        try {
            this.categoryRepository.add(category);
        } catch (RepositoryException e) {
            throw new CategoryServiceException(e);
        }
    }

    @Override
    public void update(Category category) throws ValidationException, CategoryServiceException {
        ValidationUtil.isPresented(category);
        try {
            this.categoryRepository.update(category);
        } catch (RepositoryException e) {
            throw new CategoryServiceException(e);
        }
    }

    @Override
    public void delete(long categoryId) throws ValidationException, CategoryServiceException {
        ValidationUtil.isZeroOrLess(categoryId);
        try {
            this.categoryRepository.removeById(categoryId);
        } catch (RepositoryException e) {
            throw new CategoryServiceException(e);
        }
    }

    @Override
    public Category get(long categoryId) throws ValidationException, CategoryServiceException {
        ValidationUtil.isZeroOrLess(categoryId);
        try {
            return this.categoryRepository.find(categoryId).get();
        } catch (RepositoryException e) {
            throw new CategoryServiceException(e);
        }
    }

}
