package com.example.demo.repository;

import com.example.demo.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepo extends JpaRepository<TrainingSession, Integer> {
}
