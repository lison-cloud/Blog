package by.bsuir.blog.mapper.impl;

import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaDelete;
import by.bsuir.blog.mapper.CriteriaInsert;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.CriteriaUpdate;
import by.bsuir.blog.mapper.Predicate;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.CriteriaBuilderException;
import by.bsuir.blog.mapper.exception.CriteriaDeleteException;
import by.bsuir.blog.mapper.exception.CriteriaInsertException;
import by.bsuir.blog.mapper.exception.CriteriaSelectException;
import by.bsuir.blog.mapper.exception.CriteriaUpdateException;

public class CriteriaBuilderImpl
        implements CriteriaBuilder {

    private static CriteriaBuilder instance;

    private CriteriaBuilderImpl() {
    }

    public static CriteriaBuilder newInstance() {
        if (instance == null) {
            synchronized (CriteriaBuilderImpl.class) {
                if (instance == null) {
                    instance = new CriteriaBuilderImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> CriteriaSelect<T> createSelect(Table entityTable) {
        try {
            return CriteriaSelectImpl.criteria(entityTable);
        } catch (CriteriaSelectException e) {
            throw new CriteriaBuilderException(e);
        }
    }

    @Override
    public CriteriaDelete createDelete(Table entityTable) {
        try {
            return CriteriaDeleteImpl.criteria(entityTable);
        } catch (CriteriaDeleteException e) {
            throw new CriteriaBuilderException(e);
        }
    }

    @Override
    public CriteriaUpdate createUpdate(Table entityTable) {
        try {
            return CriteriaUpdateImpl.criteria(entityTable);
        } catch (CriteriaUpdateException e) {
            throw new CriteriaBuilderException(e);
        }
    }

    @Override
    public CriteriaInsert createInsert(Table entityTable) {
        try {
            return CriteriaInsertImpl.getCriteria(entityTable);
        } catch (CriteriaInsertException e) {
            throw new CriteriaBuilderException(e);
        }
    }

    @Override
    public Predicate equal(String field, Object object) {
        Predicate p = null;
        try {
            p = PredicateImpl.getPredicate(field, object, "=");
        } catch (Exception e) {
            throw new CriteriaBuilderException("cannot create predicate", e);
        }
        return p;
    }

}
