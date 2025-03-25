package com.filmyai.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.LoginRequestDto;
import com.filmyai.backend.dto.SignupRequestDto;
import com.filmyai.backend.model.MyAppUser;
import com.filmyai.backend.service.LoginService;
import com.filmyai.backend.service.SignupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private SignupService signupService;
	
	@Autowired 
	private LoginService loginService;
	
	public AuthController(SignupService signupService, LoginService loginService) {
		this.signupService = signupService;
		this.loginService = loginService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequestDto signupRequest) {
		try {
			// Convert DTO to entity
			MyAppUser user = signupService.registerUser(signupRequest);
			
			// Save user in database (repository save method)
			//signupService.registerUser(signupRequest);
			//UserRepository.save(user);
			
			return ResponseEntity.ok("User Registered Successfully!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
		try {
			MyAppUser user = loginService.loginUser(loginRequestDto);
			return ResponseEntity.ok("Login successful");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
		}
	}
	

}
