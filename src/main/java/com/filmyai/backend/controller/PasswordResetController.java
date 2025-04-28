package com.filmyai.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.filmyai.backend.service.PasswordResetService;

@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;

	public PasswordResetController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/forgot")
	public ResponseEntity<?> forgot(@RequestParam("email") String email) {
		try {
			System.out.println(".....................");
			passwordResetService.sendPasswordResetLink(email);

			return ResponseEntity.ok("Reset link sent to your email.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	@PostMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
		try {
			passwordResetService.resetPassword(token, newPassword, confirmPassword);

			return ResponseEntity.ok("Password reset successfully!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
