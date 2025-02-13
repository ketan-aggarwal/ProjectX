package com.example.projectx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public String getEmail() {
        return email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false, unique = true)
    @JsonProperty("password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("role")
    private Role role;
    @Column(nullable = false, updatable = false)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;



    // This method will be called before the entity is persisted (inserted into DB)
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }

    public Long getId() {
        return this.id;
    }

    public Role getRole() {
        return this.role;
    }
    public String getPassword() {
        return this.password;
    }

    public enum Role {
        ASKER, RESPONDER
    }
}
