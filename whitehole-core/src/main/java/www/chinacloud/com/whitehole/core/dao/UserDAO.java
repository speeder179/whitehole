package www.chinacloud.com.whitehole.core.dao;

import com.google.common.base.Optional;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import www.chinacloud.com.whitehole.core.entity.User;
import www.chinacloud.com.whitehole.core.entity.UserMapper;

import java.util.List;
import java.util.UUID;

/**
 * Created by Speeder on 2015/11/9.
 * User DAO
 */
@RegisterMapper(UserMapper.class)
public abstract class UserDAO {

    @SqlUpdate("create table if not exists t_user(id varchar(36) primary key, name varchar(255) not null, email varchar(255) not null) ENGINE=InnoDB")
    public abstract void createUserTable();

    @SqlUpdate("drop table t_user")
    public abstract void dropUserTable();

    @SqlQuery("select * from t_user where id = :id")
    public abstract User findUserById(@Bind("id") Optional<String> id);

    @SqlQuery("select * from t_user where name like :name and email like :email")
    public abstract List<User> getUsers(@Bind("name") Optional<String> name, @Bind("email") Optional<String> email);

    @SqlUpdate("insert into t_user(id, name, email) values(:id, :name, :email)")
    protected abstract int addUser(@BindBean User user);

    @SqlUpdate("delete from t_user where id = :id")
    public abstract int deleteUserById(@Bind("id") Optional<String> id);

    @Transaction
    public void addTwoUser(User user) {
        addUser(user);
        user.setId(UUID.randomUUID().toString());
        addUser(user);
    }
}
