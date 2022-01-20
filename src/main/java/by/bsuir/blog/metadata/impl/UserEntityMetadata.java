package by.bsuir.blog.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class UserEntityMetadata
        implements Table {

    private final static String table = "user";

    private static final String idField = "u_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(UserEntity::getId, UserEntity::setId, Long.class, false));
        fields.put("u_email", new DefaultField<>(UserEntity::getEmail, UserEntity::setEmail, String.class, false));
        fields.put("u_login", new DefaultField<>(UserEntity::getLogin, UserEntity::setLogin, String.class, false));
        fields.put("u_passwordHash", new DefaultField<>(UserEntity::getHashPassword, UserEntity::setHashPassword, String.class, false));
        fields.put("u_ur_id", new DefaultField<>(UserEntity::getUserRoleId, UserEntity::setUserRoleId, Long.class, false));
        fields.put("u_ui_id", new DefaultField<>(UserEntity::getUserInfoId, UserEntity::setUserInfoId, Long.class, true));

        col = fields.keySet();
    }

    @Override
    public Constructor<UserEntity> constructor() {
        return UserEntity::new;
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
