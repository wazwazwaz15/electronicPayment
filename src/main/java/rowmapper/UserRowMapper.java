package rowmapper;

import model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
          user.setUserId(rs.getInt("user_id"));
          user.setUserName(rs.getString("username"));
          user.setEmail(rs.getString("email"));
          user.setPassword(rs.getString("password"));
        return user;
    }
}
