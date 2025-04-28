package com.filmyai.backend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.LoginRequestDto;
import com.filmyai.backend.model.MyAppUser;
import com.filmyai.backend.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public MyAppUser loginUser(LoginRequestDto loginRequestDto) {
		// Fetch user from the database based on email
		Optional<MyAppUser> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());

		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		MyAppUser user = userOptional.get();
				
		// Verify the provided password against the stored (encoded) password
		if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials!");
		}
		
		return user; // Return the authenticated user object
	}

}
