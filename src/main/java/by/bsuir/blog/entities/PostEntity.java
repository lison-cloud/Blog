package by.bsuir.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * PostEntity
 */
public class PostEntity
        implements Serializable {

    private long id;
    private String title;
    private String slug;
    private String content;

    private boolean published;
    private Timestamp publishedAt;
    private Timestamp createdAt;
    private Timestamp updateAt;

    private long userId;

    private long categoryId;

    public PostEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "PostEntity [categoryId=" + categoryId + ", content=" + content + ", createdAt=" + createdAt + ", id="
                + id + ", isPublished=" + published + ", publishedAt=" + publishedAt + ", slug=" + slug + ", title="
                + title + ", updateAt=" + updateAt + ", userId=" + userId + "]";
    }

    

}