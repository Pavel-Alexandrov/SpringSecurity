package crud.service;

import crud.dao.RoleDao;
import crud.dao.UserDao;
import crud.model.Role;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Этот класс создан для добавления закодированного юзера
@Service
public class TestAddService {
    @Autowired
    public UserDao userDao;

    @Autowired
    public RoleDao roleDao;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    public void fillTable(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
        roleDao.addRole(role);
    }
}
