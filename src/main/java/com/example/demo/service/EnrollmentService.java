package com.example.demo.service;



import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;

public interface EnrollmentService
{
  public Enrollment saveEnrollment(Enrollment enrollment);
  public void removeSessionMessage();
}
