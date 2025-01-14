package com.example.projectx.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users") // Specifies the collection name in MongoDB
public class MongoConnect {
    @Id
    private String id;
    @JsonProperty("name")// MongoDB uses '_id' as the primary key by default
    private String name;
    @JsonProperty("email")
    private String email;
}
