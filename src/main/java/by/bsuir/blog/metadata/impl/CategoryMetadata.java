package by.bsuir.blog.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class CategoryMetadata
        implements Table {

    private final static String table = "category";

    private static final String idField = "c_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(Category::getId, Category::setId, Long.class, false));
        fields.put("c_title", new DefaultField<>(Category::getTitle, Category::setTitle, String.class, false));
        fields.put("c_slug", new DefaultField<>(Category::getSlug, Category::setSlug, String.class, false));

        col = fields.keySet();
    }

    @Override
    public Constructor<Category> constructor() {
        return Category::new;
    }

    public String name() {
        return table;
    }

    public Field<Object, Object> get(String field) {
        return fields.get(field);
    }

    public String id() {
        return idField;
    }

    public Set<String> columns() {
        return col;
    }

    @Override
    public String getColumn(String column) {
        if (column == null) {
            throw new NullPointerException("column is null");
        } else if (column.length() == 0) {
            throw new IllegalArgumentException("column is zero length");
        }

        if (!col.contains(column)) {
            throw new NoSuchColumnException(column);
        }
        return column;
    }
}
