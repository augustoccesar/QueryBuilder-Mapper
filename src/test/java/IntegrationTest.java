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

            String createTable = "CREATE TABLE users (id INTEGER, name VARCHAR);";
            String insertSample = "INSERT INTO users (id, name) VALUES (1, 'Augusto Cesar');";

            connection.prepareStatement(createTable).executeUpdate();
            connection.prepareStatement(insertSample).executeUpdate();


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
    public void shouldMapResult() {
        String sql = "SELECT u.id AS u_id, u.name AS u_name FROM users u";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new Mapper<User>("u").map(rs, User.class);
                    assertEquals(1, user.getId());
                    assertEquals("Augusto Cesar", user.getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
