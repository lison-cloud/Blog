package by.bsuir.blog.entities;

import java.io.Serializable;

public class UserEntity
        implements Serializable {

    private long id;
    private String email;
    private String login;
    private String hashPassword;
    
    private long userInfoId;
    private long userRoleId;

    public UserEntity() {
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
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

    public long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "UserEntity [hashPassword=" + hashPassword + ", id=" + id + ", login=" + login + ", userInfoId="
                + userInfoId + ", userRoleId=" + userRoleId + "]";
    }

    

}
