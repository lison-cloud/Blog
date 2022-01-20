package by.bsuir.blog.metadata;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.dto.Tag;
import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.dto.UserRole;
import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.mapper.Metadata;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.metadata.impl.CategoryMetadata;
import by.bsuir.blog.metadata.impl.PostCommentEntityMetadata;
import by.bsuir.blog.metadata.impl.PostEntityMetadata;
import by.bsuir.blog.metadata.impl.TagMetadata;
import by.bsuir.blog.metadata.impl.UserEntityMetadata;
import by.bsuir.blog.metadata.impl.UserInfoMetadata;
import by.bsuir.blog.metadata.impl.UserRoleMetadata;

public class BlogMetadata
        implements Metadata {

    private static Metadata appMetadata;
    
    private static final Map<Class<?>, Table> metadata = new HashMap<>();

    static {
        metadata.put(UserEntity.class, new UserEntityMetadata());
        metadata.put(PostEntity.class, new PostEntityMetadata());
        metadata.put(PostCommentEntity.class, new PostCommentEntityMetadata());
        metadata.put(UserRole.class, new UserRoleMetadata());
        metadata.put(UserInfo.class, new UserInfoMetadata());
        metadata.put(Tag.class, new TagMetadata());
        metadata.put(Category.class, new CategoryMetadata());
    }

    public static Metadata newInstance() {
        if(appMetadata==null)
            appMetadata = new BlogMetadata();
        return appMetadata;
    }

    private BlogMetadata(){}

    @Override
    public Table entityTable(Class<?> clazz) {
        Table t = metadata.get(clazz);
        if(t==null) {
            throw new RuntimeException("no metadata found for clazz: " + clazz.getName());
        }
        return t;
    }

}
