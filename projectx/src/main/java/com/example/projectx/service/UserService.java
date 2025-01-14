package com.example.projectx.service;

import com.example.projectx.entity.MongoConnect;
import com.example.projectx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<MongoConnect> getAllUsers() {
        return userRepository.findAll();  // Fetches all users from MongoDB
    }

    public MongoConnect createUser(MongoConnect user) {
        return userRepository.save(user);  // Saves a new user to MongoDB
    }
}
