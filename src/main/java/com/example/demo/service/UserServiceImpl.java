package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService 
{ @Autowired
   private UserRepo userRepo;

   @Autowired
private BCryptPasswordEncoder passwordEncoder;
	@Override
	public User saveUser(User user) {
		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		User u= userRepo.save(user);
		return u;
	}
	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

	
}
