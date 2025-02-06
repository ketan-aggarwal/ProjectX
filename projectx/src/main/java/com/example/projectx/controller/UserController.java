package com.example.projectx.controller;

import com.example.projectx.entity.User;
import com.example.projectx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
       System.out.println("Inside Login FUnction");
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        if (userService.authenticate(email, password)) {
            Optional<User> user = userService.findByEmail(email);
            return ResponseEntity.ok(Map.of(
                    "authenticated", true,
                    "email", user.get().getEmail(),
                    "password", user.get().getPassword()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("authenticated", false, "message", "Invalid email or password"));
        }
    }
}
