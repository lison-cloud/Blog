package by.bsuir.blog.metadata.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.NoSuchColumnException;

public class PostEntityMetadata 
    implements Table{

    private final static String table = "post";

    private static final String idField = "p_id";

    private static final Map<String, Field> fields = new HashMap<>();

    private static final Set<String> col;

    static {
        fields.put(idField, new DefaultField<>(PostEntity::getId, PostEntity::setId, Long.class, false));
        fields.put("p_u_id", new DefaultField<>(PostEntity::getUserId, PostEntity::setUserId, Long.class, false));
        fields.put("p_c_id", new DefaultField<>(PostEntity::getCategoryId, PostEntity::setCategoryId, Long.class, false));
        fields.put("p_title", new DefaultField<>(PostEntity::getTitle, PostEntity::setTitle, String.class, false));
        fields.put("p_slug", new DefaultField<>(PostEntity::getSlug, PostEntity::setSlug, String.class, false));
        fields.put("p_published", new DefaultField<>(PostEntity::isPublished, PostEntity::setPublished, Boolean.class, true));
        fields.put("p_content", new DefaultField<>(PostEntity::getContent, PostEntity::setContent, String.class, true));
        fields.put("p_createdAt", new DefaultField<>(PostEntity::getCreatedAt, PostEntity::setCreatedAt, Timestamp.class, false));
        fields.put("p_updatedAt", new DefaultField<>(PostEntity::getUpdateAt, PostEntity::setUpdateAt, Timestamp.class, true));
        fields.put("p_publishedAt", new DefaultField<>(PostEntity::getPublishedAt, PostEntity::setPublishedAt, Timestamp.class, true));
        
        col = fields.keySet();
    }

    @Override
    public Constructor<PostEntity> constructor() {
        return PostEntity::new;
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
