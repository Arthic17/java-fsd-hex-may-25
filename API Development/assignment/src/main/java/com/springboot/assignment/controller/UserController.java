package com.springboot.assignment.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.User;
import com.springboot.assignment.service.UserService;
import com.springboot.assignment.util.JwtUtil;

@RestController
public class UserController {

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	@PostMapping("/api/user/signup")
	public User signup(@RequestBody User user) {
		return userService.signup(user);
	}
	
	
	@GetMapping("/api/user/token")
	public ResponseEntity<?> getToken(Principal principal){
		try {
		String token =jwtUtil.createToken(principal.getName());
		return ResponseEntity.status(HttpStatus.OK).body(token);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
}
