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
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("questionText")
    private String questionText; // The content of the question


//    @Column(nullable = false)
//    @JsonProperty("asker_id")
//    private long asker_id;

    @ManyToOne
    @JoinColumn(name = "asker_id", nullable = false)
    @JsonProperty("asker")
    private User asker;

    @Column(nullable = false)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt; // Timestamp of when the question was asked

    @Column(nullable = false)
    @JsonProperty("isAnswered")
    private Boolean isAnswered = false; // Whether the question has been answered or not

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }
}
