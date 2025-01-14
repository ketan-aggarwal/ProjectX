package com.example.projectx.controller;

import com.example.projectx.entity.MongoConnect;
import com.example.projectx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<MongoConnect> getAllUsers() {
        return userService.getAllUsers();  // Should return list of users
    }

    @PostMapping
    public MongoConnect createUser(@RequestBody MongoConnect user) {
        return userService.createUser(user);  // Should create a new user
    }
}
