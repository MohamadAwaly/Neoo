package be.neoo.controller;

import be.neoo.entities.User;
import be.neoo.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }
    @GetMapping("/display")
    public List<User> displayUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id")  int id) {
        userService.deleteUser(id);
    }
}
