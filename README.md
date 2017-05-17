# QueryBuilder Mapper
[![Build Status](https://travis-ci.org/augustoccesar/QueryBuilder-Mapper.svg?branch=master)](https://travis-ci.org/augustoccesar/QueryBuilder-Mapper)
[![GitHub version](https://badge.fury.io/gh/augustoccesar%2FQueryBuilder-Mapper.svg)](https://badge.fury.io/gh/augustoccesar%2FQueryBuilder-Mapper)

Project created to try to build a better way to map result from database query
to Java objects.

## Usage
Instead of having to map each column from the result like:
```java
String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1"
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            models.querybuilderannotation.User user = new Mapper<models.querybuilderannotation.User>("u").map(rs, models.querybuilderannotation.User.class);
            models.querybuilderannotation.UserProfile userProfile = new Mapper<models.querybuilderannotation.UserProfile>("up").map(rs, models.querybuilderannotation.UserProfile.class);

            models.querybuilderannotation.User user = new models.querybuilderannotation.User();
            user.setId(rs.getInt("u_id"));
            user.setName(rs.getString("u_name"));

            models.querybuilderannotation.UserProfile userProfile = new models.querybuilderannotation.UserProfile();
            userProfile.setGender(rs.getString("up_gender"));
            userProfile.setAge(rs.getInt("up_age"));

            user.setUserProfile(userProfile);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

You can call the `Mapper` as follow to automatically bind the result into the java object.
```java
String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1"
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            models.querybuilderannotation.User user = new Mapper<models.querybuilderannotation.User>("u").map(rs, models.querybuilderannotation.User.class);
            models.querybuilderannotation.UserProfile userProfile = new Mapper<models.querybuilderannotation.UserProfile>("up").map(rs, models.querybuilderannotation.UserProfile.class);

            user.setUserProfile(userProfile);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

For this to work, you only have to set the annotations on the class like (for the example above):
```java
public class models.querybuilderannotation.User {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    private models.querybuilderannotation.UserProfile userProfile;

    // getter and setters omitted
}
```
```java
public class models.querybuilderannotation.UserProfile {
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;

    // getter and setters omitted
}
```