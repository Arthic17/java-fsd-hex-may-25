package com.springboot.assignment.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.springboot.assignment.model.User;
import com.springboot.assignment.repository.UserRepository;

@Service
public class UserService {
  
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


		public User signup(User user) { 
		String plainPassword = user.getPassword(); 
		String encodedPassword =  passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); 
		return userRepository.save(user);
	}
		
		
}
