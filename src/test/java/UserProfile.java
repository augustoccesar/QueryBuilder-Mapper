import br.com.augustoccesar.querybuilder.mapper.annotations.Column;
import br.com.augustoccesar.querybuilder.mapper.annotations.Table;
import br.com.augustoccesar.querybuilder.mapper.models.ColumnType;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
