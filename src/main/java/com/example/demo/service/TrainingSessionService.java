package com.example.demo.service;

import com.example.demo.entity.TrainingSession;
import com.example.demo.entity.User;

public interface TrainingSessionService {
    public TrainingSession saveTrainingSession(TrainingSession trainingSession);
    public void removeSessionMessage();
}
