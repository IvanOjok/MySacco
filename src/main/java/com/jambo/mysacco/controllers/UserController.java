package com.jambo.mysacco.controllers;


import com.jambo.mysacco.models.User;
import com.jambo.mysacco.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

//    @PostMapping("{login}")
//    public User userLogin(@RequestBody User user) {
//
//        return userService.getUser(userId);
//    }

    @PostMapping()
    public String addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("")
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
