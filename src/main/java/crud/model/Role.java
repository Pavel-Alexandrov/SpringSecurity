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

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "roles")
    private Set<User> users = new HashSet<>(0);

    public Role() {
    }

    public Role(int id, String access, Set<User> users) {
        this.roleId = id;
        this.access = access;
        this.users = users;
    }

    public Role(String access, Set<User> users) {
        this.access = access;
        this.users = users;
    }

    public int getId() {
        return roleId;
    }

    public String getAccess() {
        return access;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return access;
    }
}
