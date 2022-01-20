package by.bsuir.blog.repository.impl;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.CategoryRepository;

public class CategoryRepositoryImpl
        extends AbstractBaseRepository<Category>
        implements CategoryRepository {

    private static CategoryRepository instance;

    private CategoryRepositoryImpl() {
        super(Category.class);
    }

    public static CategoryRepository getInstance() {
        if (instance == null) {
            synchronized (CategoryRepositoryImpl.class) {
                if (instance == null) {
                    instance = new CategoryRepositoryImpl();
                }
            }
        }
        return instance;
    }

}
