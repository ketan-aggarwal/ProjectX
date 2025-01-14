package com.example.projectx.repository;

import com.example.projectx.entity.MongoConnect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<MongoConnect, String> {
    // Custom queries can go here if needed
}
