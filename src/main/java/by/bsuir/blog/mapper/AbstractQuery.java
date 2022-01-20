package by.bsuir.blog.mapper;

public interface AbstractQuery
        extends BaseQuery {

    AbstractQuery where(Predicate expression);

}
