package by.bsuir.blog.metadata.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class UserInfoMetadata
        implements Table {

    private final static String table = "user_info";

    private static final String idField = "ui_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(UserInfo::getId, UserInfo::setId, Long.class, false));
        fields.put("ui_name", new DefaultField<>(UserInfo::getName, UserInfo::setName, String.class, true));
        fields.put("ui_surname", new DefaultField<>(UserInfo::getSurname, UserInfo::setSurname, String.class, true));
        fields.put("ui_registeredAt",
                new DefaultField<>(UserInfo::getRegisteredAt, UserInfo::setRegisteredAt, Timestamp.class, false));

        col = fields.keySet();
    }

    @Override
    public Constructor<UserInfo> constructor() {
        return UserInfo::new;
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
