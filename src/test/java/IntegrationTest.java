import br.com.augustoccesar.querybuilder.mapper.mapper.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.*;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class IntegrationTest {
    private Connection connection;

    @Before
    public void setConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            String createTableUser = "CREATE TABLE users (id INTEGER, name VARCHAR);";
            String createTableUserProfile = "CREATE TABLE users_profile (id INTEGER, user_id INTEGER, gender VARCHAR, age INTEGER, FOREIGN KEY (user_id) REFERENCES users(id));";

            String insertSampleUser = "INSERT INTO users (id, name) VALUES (1, 'Augusto Cesar');";
            String insertSampleUserProfile = "INSERT INTO users_profile (id, user_id, gender, age) VALUES (1, 1, 'male', 23);";

            connection.prepareStatement(createTableUser).executeUpdate();
            connection.prepareStatement(createTableUserProfile).executeUpdate();

            connection.prepareStatement(insertSampleUser).executeUpdate();
            connection.prepareStatement(insertSampleUserProfile).executeUpdate();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @After
    public void deleteDatabase() {
        File file = new File("test.db");
        file.delete();
    }

    @Test
    public void shouldMapResultQueryBuilderAnnotation() {
        String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    models.querybuilderannotation.User user = new Mapper<models.querybuilderannotation.User>("u").map(rs, models.querybuilderannotation.User.class);
                    models.querybuilderannotation.UserProfile userProfile = new Mapper<models.querybuilderannotation.UserProfile>("up").map(rs, models.querybuilderannotation.UserProfile.class);

                    user.setUserProfile(userProfile);

                    assertEquals(1, user.getId());
                    assertEquals("Augusto Cesar", user.getName());
                    assertEquals("male", user.getUserProfile().getGender());
                    assertEquals(23, user.getUserProfile().getAge());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldMapResultQueryBuilderJPAAnnotation() {
        String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    models.jpaannotation.User user = new Mapper<models.jpaannotation.User>("u").map(rs, models.jpaannotation.User.class);
                    models.jpaannotation.UserProfile userProfile = new Mapper<models.jpaannotation.UserProfile>("up").map(rs, models.jpaannotation.UserProfile.class);

                    user.setUserProfile(userProfile);

                    assertEquals(1, user.getId());
                    assertEquals("Augusto Cesar", user.getName());
                    assertEquals("male", user.getUserProfile().getGender());
                    assertEquals(23, user.getUserProfile().getAge());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldMapResultQueryBuilderWithoutAnnotation() {
        String sql = "SELECT u.id AS u_id, u.name AS u_name, up.gender AS up_gender, up.age AS up_age FROM users u INNER JOIN users_profile up ON u.id = up.user_id WHERE u.id = 1";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    models.withoutannotation.User user = new Mapper<models.withoutannotation.User>("u").map(rs, models.withoutannotation.User.class);
                    models.withoutannotation.UserProfile userProfile = new Mapper<models.withoutannotation.UserProfile>("up").map(rs, models.withoutannotation.UserProfile.class);

                    user.setUserProfile(userProfile);

                    assertEquals(1, user.getId());
                    assertEquals("Augusto Cesar", user.getName());
                    assertEquals("male", user.getUserProfile().getGender());
                    assertEquals(23, user.getUserProfile().getAge());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
