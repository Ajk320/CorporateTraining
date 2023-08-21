package com.example.demo.repository;

import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Integer> {

    public Enrollment findByUserIdAndTrainingSessionId(Integer userId, Integer trainingSessionId);
}
