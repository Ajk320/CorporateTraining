package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String name;

    public LocalDateTime startDate;
    public LocalDateTime endDate;

    public String description;

    public String speaker;

    public String streetAddress;
    public String city;
    public String state;
    public String postalCode;
    @Column(columnDefinition="TEXT")
    public String location;

    @OneToMany(mappedBy = "trainingSession")
    private Set<Enrollment> enrollments = new HashSet<>();
}
