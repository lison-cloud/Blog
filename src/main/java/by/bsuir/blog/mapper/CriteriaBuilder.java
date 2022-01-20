package by.bsuir.blog.mapper;

public interface CriteriaBuilder {

    <T> CriteriaSelect<T> createSelect(Table entityTable);

    CriteriaDelete createDelete(Table entityTable);

    CriteriaUpdate createUpdate(Table entityTable);

    CriteriaInsert createInsert(Table entityTable);

    Predicate equal(String field, Object object);

}
