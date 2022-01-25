package by.bsuir.blog.repository.impl;

import java.util.Optional;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.CategoryRepository;
import by.bsuir.blog.repository.exception.CategoryRepositoryException;

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

    @Override
    public Optional<Category> getBySlug(String slug) throws CategoryRepositoryException {
        CriteriaBuilder cb = this.entityManager.criteriaBuilder();
        CriteriaSelect<Category> cs = cb.createSelect(this.table);
        try {
            return Optional.ofNullable(
                    this.entityManager.createQuery(cs.select(cs.where(cb.equal(this.table.getColumn("c_slug"), slug))))
                            .getSingleResult());
        } catch (MapperException e) {
            throw new CategoryRepositoryException(e);
        }
    }

}
