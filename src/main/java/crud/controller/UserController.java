package crud.controller;

import crud.model.User;
import crud.service.TestAddService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public TestAddService testAddService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth() {
       // return "/codeAdd";
        return "/authentication";
    }

    /*@RequestMapping(value = "/log", method = RequestMethod.GET)
    public String test2() {
         return "/codeAdd";
        //return "/auth";
    }*/

    //этот метод создан для добавления закодированного юзера
//    @RequestMapping(value = "/testAdd", method = RequestMethod.POST)
//    public String test2(@ModelAttribute("name") String name,
//                        @ModelAttribute("login") String login,
//                        @ModelAttribute("password") String password,
//                        @ModelAttribute("access") String access) {
//        User user = new User(name, login, password, new HashSet<Role>());
//        Role role = new Role(access, new HashSet<User>());
//
//        HashSet<Role> roleHashSet = new HashSet<>();
//        roleHashSet.add(role);
//        user.setRoles(roleHashSet);
//
//        HashSet<User> userHashSet = new HashSet<>();
//        userHashSet.add(user);
//        role.setUsers(userHashSet);
//
//        user.setRoles(roleHashSet);
//        role.setUsers(userHashSet);
//
//        testAddService.fillTable(user, role);
//        return "/codeAdd";
//    }



    //Юзерские страницы

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String UserProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        return "/user/profile";

    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String userUpdateGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/user/update";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String userUpdatePost(@ModelAttribute("user") User user) {
        userService.updateUser(user);

        return "/user/profile";
    }

    //Админские страницы
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        model.addAttribute("user", new User());

        return "/admin/add";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") User user, Model model) {
        userService.addUser(user);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public String updateUserGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/admin/update";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }


}
