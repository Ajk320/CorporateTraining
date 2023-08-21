package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user.id")
    public User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    public TrainingSession trainingSession;

    public LocalDateTime enrollmentDate;
}
