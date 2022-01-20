package by.bsuir.blog.metadata.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class PostCommentEntityMetadata
        implements Table {

    private static final String table = "post_comment";

    private static final String idField = "pc_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(PostCommentEntity::getId, PostCommentEntity::setId, Long.class, false));
        fields.put("pc_text", new DefaultField<>(PostCommentEntity::getText, PostCommentEntity::setText, String.class, false));
        fields.put("pc_publishedAt",
                new DefaultField<>(PostCommentEntity::getPublishedAt, PostCommentEntity::setPublishedAt, Timestamp.class, false));
        fields.put("pc_u_login",
                new DefaultField<>(PostCommentEntity::getUserLogin, PostCommentEntity::setUserLogin, String.class, false));
        fields.put("pc_p_id",
                new DefaultField<>(PostCommentEntity::getPostId, PostCommentEntity::setPostId, Long.class, false));

        col = fields.keySet();
    }

    @Override
    public Constructor<PostCommentEntity> constructor() {
        return PostCommentEntity::new;
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
