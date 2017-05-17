# QueryBuilder Mapper
[![Build Status](https://travis-ci.org/augustoccesar/QueryBuilder-Mapper.svg?branch=master)](https://travis-ci.org/augustoccesar/QueryBuilder-Mapper)
[![GitHub version](https://badge.fury.io/gh/augustoccesar%2FQueryBuilder-Mapper.svg)](https://badge.fury.io/gh/augustoccesar%2FQueryBuilder-Mapper)

Project created to try to build a better way to map result from database query
to Java objects.

## Mapper creation
The mapper can be created in one of the ways
```java
User user = new Mapper<User>("u").map(resultSet, User.class);
```

```java
User user = new User();
new Mapper<User>("u").map(resultSet, user);
```

## Mapper Usage
Instead of having to map each column from the result like:
```java
String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1"
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            User user = new Mapper<User>("u").map(rs, User.class);
            UserProfile userProfile = new Mapper<UserProfile>("up").map(rs, UserProfile.class);

            User user = new User();
            user.setId(rs.getInt("u_id"));
            user.setName(rs.getString("u_name"));

            UserProfile userProfile = new UserProfile();
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
            User user = new Mapper<User>("u").map(rs, User.class);
            UserProfile userProfile = new Mapper<UserProfile>("up").map(rs, UserProfile.class);

            user.setUserProfile(userProfile);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

For this to work, you only have to set the annotations on the class like (for the example above):
```java
public class User {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    private UserProfile userProfile;

    // getter and setters omitted
}
```
```java
public class UserProfile {
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

## Executor Usage
Executor is used to reduce even more the quantity of code written to run simple queries, it's missing some more complex
configurations yet, but for simple queries instead of having to run
```java
String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = ?"
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    stmt.setInt(1, 1);
    try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            User user = new Mapper<User>("u").map(rs, User.class);
            UserProfile userProfile = new Mapper<UserProfile>("up").map(rs, UserProfile.class);

            user.setUserProfile(userProfile);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```
you can run only
```java
String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1"

Executor.executeQuery(sql, connection, (rs) -> {
    User user = new Mapper<User>("u").map(rs, User.class);
    UserProfile userProfile = new Mapper<UserProfile>("up").map(rs, UserProfile.class);
    
    user.setUserProfile(userProfile);
}, 1)
```