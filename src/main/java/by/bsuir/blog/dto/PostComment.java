package by.bsuir.blog.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostComment
        implements Serializable {

    private long id;
    private String text;
    private Timestamp publishedAt;
    private String userLogin;

    public PostComment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }

    @Override
    public String toString() {
        return "PostComment [id=" + id + ", stamp=" + publishedAt + ", text=" + text + ", userLogin=" + userLogin
                + "]";
    }

}
