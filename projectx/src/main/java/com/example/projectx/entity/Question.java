package com.example.projectx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("questionText")
    private String questionText; // The content of the question

    @ManyToOne
    @JsonProperty("asker_id")
    @JoinColumn(name = "asker_id", nullable = false)
    private User asker; // The user who asked the question, references the User entity

    @Column(nullable = false)
    private LocalDateTime createdAt; // Timestamp of when the question was asked

    @Column(nullable = false)
    private Boolean isAnswered = false; // Whether the question has been answered or not

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }
}
