package by.bsuir.blog.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo
        implements Serializable {

    private long id;
    private String name;
    private String surname;
    private Timestamp registeredAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", joinDate=" + registeredAt + ", name=" + name
                + ", surname="
                + surname + "]";
    }

}
