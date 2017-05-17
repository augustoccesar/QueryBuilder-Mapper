package models.querybuilderannotation;

import br.com.augustoccesar.querybuilder.mapper.annotations.Column;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
public class User {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
