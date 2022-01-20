package by.bsuir.blog.mapper;

public interface CriteriaSelect<T>
        extends AbstractQuery {

    CriteriaSelect<T> select();

    CriteriaSelect<T> select(CriteriaSelect<T> criteria);

    CriteriaSelect<T> where(Predicate expression);

    CriteriaSelect<T> orderBy(String column, boolean asc);

    Table table();

}
