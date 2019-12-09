package crud.model;

import crud.dao.RoleDao;
import crud.dao.RoleDaoImpl;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Resource
@Table(name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    //добавить поле role many-to-many ets
    //list/set roles @joinTable, @joinColumn
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "userID", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "roleID", referencedColumnName = "roleID")
    )
    private Set<Role> roles = new HashSet<>(0);

    public User(int id, String name, String login, String password, Set<Role> roles) {
        this.userId = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String login, String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    //getters
    public int getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    //тут как то криво
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new RoleDaoImpl().getRoleByLogin(this.getLogin()));
//
//        return grantedAuthorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //setters
    public void setId(int id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

