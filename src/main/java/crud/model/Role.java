package crud.model;


import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Resource
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "access")
    private String access;

    public Role() {
    }

    public Role(String login, String access) {
        this.login = login;
        this.access = access;
    }

    public String getLogin() {
        return login;
    }

    public String getAccess() {
        return access;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String getAuthority() {
        return access;
    }
}
