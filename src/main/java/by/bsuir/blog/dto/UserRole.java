package by.bsuir.blog.dto;

import java.io.Serializable;

public class UserRole
    implements Serializable {
    
    private long id;
    private String role;
    
    public UserRole() {
    }

    public UserRole(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
