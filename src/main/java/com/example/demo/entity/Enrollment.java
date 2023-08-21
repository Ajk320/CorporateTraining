package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Enrollment {

    public Enrollment() {

    }

    public Enrollment(User user, TrainingSession trainingSession) {
        this.user = user;
        this.trainingSession = trainingSession;
        this.enrollmentDate = LocalDateTime.now();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

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
