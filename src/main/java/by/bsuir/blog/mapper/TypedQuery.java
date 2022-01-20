package by.bsuir.blog.mapper;

import java.util.List;

public interface TypedQuery<T>
        extends Query {

    T getSingleResult();

    List<T> getResultList();

}
