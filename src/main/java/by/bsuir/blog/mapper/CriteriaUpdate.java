package by.bsuir.blog.mapper;

public interface CriteriaUpdate
        extends AbstractQuery {

    CriteriaUpdate set(Object object);

    CriteriaUpdate where(Predicate expression);

}
