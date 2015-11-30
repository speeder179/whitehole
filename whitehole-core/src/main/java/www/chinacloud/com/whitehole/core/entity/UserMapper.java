package www.chinacloud.com.whitehole.core.entity;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Speeder on 2015/11/9.
 */
public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}
