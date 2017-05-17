package models.withoutannotation;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
public class User {
    private int id;
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
