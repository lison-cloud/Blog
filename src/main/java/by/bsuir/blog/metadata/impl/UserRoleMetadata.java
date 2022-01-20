package by.bsuir.blog.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.dto.UserRole;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class UserRoleMetadata
        implements Table {

    private final static String table = "user_role";

    private static final String idField = "ur_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(UserRole::getId, UserRole::setId, Long.class, false));
        fields.put("ur_role", new DefaultField<>(UserRole::getRole, UserRole::setRole, String.class, false));

        col = fields.keySet();
    }

    @Override
    public Constructor<UserRole> constructor() {
        return UserRole::new;
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
