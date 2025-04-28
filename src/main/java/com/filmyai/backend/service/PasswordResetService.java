package com.filmyai.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.filmyai.backend.model.MyAppUser;
import com.filmyai.backend.repository.UserRepository;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void sendPasswordResetLink(String email) {
		// Validate if email exists in the database
		Optional<MyAppUser> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email address not
			// found.");
			throw new RuntimeException("Email address not found.");
		}

		// Generate reset token and expiration time
		MyAppUser user = userOptional.get();
		String token = UUID.randomUUID().toString();
		user.setResetToken(token);
		user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(10)); // Token valid for 10 minutes
		userRepository.save(user);

		// Construct reset URL Dynamically
		String baseURL = System.getenv("AAP_BASE_URL");
		if (baseURL == null || baseURL.isBlank()) {
			baseURL = "http://localhost:8080";
		}
		String resetURL = baseURL + "/resetpassword?token=" + token;

		// Send reset email
		sendResetEmail(user.getEmail(), resetURL);
		// return ResponseEntity.ok("Password reset link sent to your email.");

	}

	// Send reset email format
	private void sendResetEmail(String email, String resetUrl) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setFrom("\"FilmyAI Support\" <saindanekuldeep16@gmail.com>"); // Explicitly set the sender's email address
		message.setSubject("Action Required: Reset Your FilmyAI Password");
		message.setText("Hello,\n\nTo reset your password for the FilmyAI, please click the link below:\n" + resetUrl
				+ "\n\nThe Link will expire in 10 Minutes. \n\n If you did not request this, please ignore this email. "
				+ "\n\n\n Regards, \n Team FilmyAI");
		mailSender.send(message);
	}

	public void resetPassword(String token, String newPassword, String confirmPassword) {
		if (!newPassword.equals(confirmPassword)) {
			throw new IllegalArgumentException("Passwords do not match.");
		}

		Optional<MyAppUser> userOptional = userRepository.findByResetToken(token);
		if (userOptional.isEmpty()) {
			 throw new IllegalArgumentException("Invalid or expired token.");
		}
		MyAppUser user = userOptional.get();

		if (user.getResetTokenExpiration() == null || user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Token has expired.");
		}

		if (!isPasswordStrong(newPassword)) {
			 throw new IllegalArgumentException("Password must be at least 8 characters, contain an uppercase letter, a lowercase letter, and a number.");
		}

		// Update password and clear token
		user.setPassword(passwordEncoder.encode(newPassword));
		user.setResetToken(null);
		user.setResetTokenExpiration(null);
		userRepository.save(user);

		//return "redirect:/login";
	}

	// Validate password strength
	private boolean isPasswordStrong(String password) {
		// Example: At least 8 characters, 1 uppercase, 1 lowercase, and 1 digit
		return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
				&& password.matches(".*\\d.*");
	}

}
