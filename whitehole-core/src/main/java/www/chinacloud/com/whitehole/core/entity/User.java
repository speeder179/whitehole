package www.chinacloud.com.whitehole.core.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by Speeder on 2015/10/30.
 */
public class User {

    private String id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
