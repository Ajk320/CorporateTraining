package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import com.example.demo.repository.EnrollmentRepo;
import com.example.demo.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class EnrollmentServiceImpl implements EnrollmentService
{ @Autowired
   private EnrollmentRepo enrollmentRepo;

	@Override
	public Enrollment saveEnrollment(Enrollment enrollment) {
		Enrollment e= enrollmentRepo.save(enrollment);
		return e;
	}
	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

	
}
