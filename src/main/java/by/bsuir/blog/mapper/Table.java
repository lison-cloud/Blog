package by.bsuir.blog.mapper;

import java.util.Set;

public interface Table {
    String name();

    Field<Object, Object> get(String field);

    String getColumn(String column);

    String id();

    Set<String> columns();

    <T> Constructor<T> constructor();
}
