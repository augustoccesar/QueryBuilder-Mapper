# QueryBuilder Mapper
Project created to try to build a better way to map result from database query
to Java objects.

## Usage
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
@Table(name = "users")
public class User {
    @Column(name = "id", type = ColumnType.INTEGER)
    private int id;
    @Column(name = "name", type = ColumnType.VARCHAR)
    private String name;

    private UserProfile userProfile;

    // getter and setters omitted
}
```
```java
@Table(name = "users_profile")
public class UserProfile {
    @Column(name = "id", type = ColumnType.INTEGER)
    private int id;
    @Column(name = "user_id", type = ColumnType.INTEGER)
    private int userId;
    @Column(name = "gender", type = ColumnType.VARCHAR)
    private String gender;
    @Column(name = "age", type = ColumnType.INTEGER)
    private int age;

    // getter and setters omitted
}
```

## Available `ColumnType`'s at the moment
```java
ColumnType.INTEGER
ColumnType.VARCHAR
ColumnType.BOOLEAN
ColumnType.TIMESTAMP
```
Right now, the `ColumnType` defined for a attribute is no used, but it will be used in the future.