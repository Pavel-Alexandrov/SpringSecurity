package crud.service;

import crud.dao.RoleDao;
import crud.dao.UserDao;
import crud.model.Role;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Autowired
    public RoleDao roleDao;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        Set<User> users = new HashSet<>();
        users.add(user);

        Role role = new Role("user", users);
        user.setPassword(passwordEncoder.encode(user.getPassword()));  //кодирование пароля при регистрации, надеюсь, в нужном месте поставил
        user.setPassword(user.getPassword());

        userDao.addUser(user);
        roleDao.addRole(role);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  //тут тоже кодирование пароля
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.deleteUser(id);
        roleDao.deleteRole(
                roleDao.getRoleByLogin(
                        user.getLogin()
                        )
        );
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Role getRoleByLogin(String login) {
        return roleDao.getRoleByLogin(login);
    }
}
