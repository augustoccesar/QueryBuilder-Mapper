package models.querybuilderannotation;

import br.com.augustoccesar.querybuilder.mapper.annotations.Column;

import java.util.Date;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
public class User {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "deleted_at")
    private Date deletedAt;

    private UserProfile userProfile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
