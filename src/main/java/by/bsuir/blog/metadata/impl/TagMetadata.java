package by.bsuir.blog.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class TagMetadata
        implements Table {

    private final static String table = "tag";

    private static final String idField = "t_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(Tag::getId, Tag::setId, Long.class, false));
        fields.put("t_title", new DefaultField<>(Tag::getTitle, Tag::setTitle, String.class, false));

        col = fields.keySet();
    }

    @Override
    public Constructor<Tag> constructor() {
        return Tag::new;
    }

    public String name() {
        return table;
    }

    public Field get(String field) {
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
