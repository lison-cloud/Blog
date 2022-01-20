package by.bsuir.blog.dto;

import java.io.Serializable;

public class Category
        implements Serializable {

    private long id;
    private String title;
    private String slug;

    public Category() {
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

    @Override
    public String toString() {
        return "Category [id=" + id + ", slug=" + slug + ", title=" + title + "]";
    }

    

}
