package com.example.demo.service;

import com.example.demo.entity.TrainingSession;
import com.example.demo.entity.User;
import com.example.demo.repository.TrainingSessionRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService{
    @Autowired
    private TrainingSessionRepo trainingSessionRepo;
    @Override
    public TrainingSession saveTrainingSession(TrainingSession trainingSession) {
       return trainingSessionRepo.save(trainingSession);
    }

    @Override
    public void removeSessionMessage() {

    }
}
