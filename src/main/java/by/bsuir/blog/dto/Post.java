package by.bsuir.blog.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.List;

public class Post
        implements Serializable {

    private long id;
    private String title;
    private String content;
    private String slug;

    private boolean published;
    private Timestamp publishedAt;
    private Timestamp createdAt;
    private Timestamp updateAt;

    private String userLogin;

    private Category category;

    private List<String> tags;

    private List<PostComment> comments;

    public Post() {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post [category=" + category + ", comments=" + comments + ", content=" + content + ", createdAt="
                + createdAt + ", id=" + id + ", published=" + published + ", publishedAt=" + publishedAt + ", slug="
                + slug + ", tags=" + tags + ", title=" + title + ", updateAt=" + updateAt + ", userLogin=" + userLogin
                + "]";
    }

    

    

}
