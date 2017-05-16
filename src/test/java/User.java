import br.com.augustoccesar.querybuilder.mapper.annotations.Column;
import br.com.augustoccesar.querybuilder.mapper.annotations.Table;
import br.com.augustoccesar.querybuilder.mapper.models.ColumnType;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
@Table(name = "users")
public class User {
    @Column(name = "id", type = ColumnType.INTEGER)
    private int id;
    @Column(name = "name", type = ColumnType.VARCHAR)
    private String name;

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
}
