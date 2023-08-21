package com.example.demo.repository;

import com.example.demo.entity.TrainingSession;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingSessionRepo extends JpaRepository<TrainingSession, Integer> {
    public List<TrainingSession> findAll();
}
