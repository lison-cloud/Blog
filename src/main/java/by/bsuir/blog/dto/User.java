package by.bsuir.blog.dto;

import java.io.Serializable;
import java.util.List;

public class User
        implements Serializable {

    private long id;
    private String email;
    private String login;
    private String hashPassword;
    private UserInfo userInfo;
    private String userRole;

    private List<Post> posts;

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User [hashPassword=" + hashPassword + ", id=" + id + ", login=" + login + ", posts=" + posts
                + ", userInfo=" + userInfo + ", userRole=" + userRole + "]";
    }

}
