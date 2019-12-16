package crud.model;


import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Resource;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Resource
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "roleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "access")
    private String access;

//    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "roles")
//    private Set<User> users = new HashSet<>(0);

    public Role() {
    }

    public Role(String access) {
        this.access = access;
    }

    public int getId() {
        return roleId;
    }

    public String getAccess() {
        return access;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String getAuthority() {
        return access;
    }
}
