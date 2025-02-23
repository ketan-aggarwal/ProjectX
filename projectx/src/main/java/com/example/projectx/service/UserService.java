package com.example.projectx.service;

import com.example.projectx.entity.User;
import com.example.projectx.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

// UserService.java
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOrCreateUser(String email, String name) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> createUser(email, name));
    }

    private User createUser(String email, String name) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setProvider("google");
        return userRepository.save(newUser);
    }
}