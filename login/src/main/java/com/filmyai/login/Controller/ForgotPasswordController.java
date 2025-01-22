package com.filmyai.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    @Autowired
    private MyAppUserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Forgot password request
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        try {
        	
            // Validate if email exists in the database
            Optional<MyAppUser> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email address not found.");
            }

            
            // Generate reset token and expiration time
            MyAppUser user = userOptional.get();
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            user.setResetTokenExpiration(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
            userRepository.save(user);

            
            // Construct reset URL dynamically
            String baseUrl = System.getenv("APP_BASE_URL"); 
            if (baseUrl == null || baseUrl.isBlank()) {
                baseUrl = "http://localhost:8080"; 
            }
            String resetUrl = baseUrl + "/reset-password?token=" + token;

            
            // Send reset email
            sendResetEmail(user.getEmail(), resetUrl);

            return ResponseEntity.ok("Password reset link sent to your email.");
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email. Please check email settings.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Send reset email format
    private void sendResetEmail(String email, String resetUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("Company email address"); // Explicitly set the sender's email address
        message.setSubject("Password Reset Request");
        message.setText("Hello,\n\nTo reset your password for the FilmyAI, please click the link below:\n" + resetUrl + "\n\nThe Link will expire in 1 hour \n\n If you did not request this, please ignore this email.");
        mailSender.send(message);
    }


    // reset password entry 
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            model.addAttribute("token", token); 
            return "reset-password";
        }

        Optional<MyAppUser> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Invalid or expired token.");
            return "reset-password";
        }

        MyAppUser user = userOptional.get();

        if (user.getResetTokenExpiration() == null || user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Token has expired.");
            return "reset-password";
        }

        if (!isPasswordStrong(newPassword)) {
            model.addAttribute("error", "Password must be at least 8 characters, contain an uppercase letter, a lowercase letter, and a number.");
            model.addAttribute("token", token);
            return "reset-password";
        }

        // Update password and clear token
        user.setPassword_hash(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiration(null);
        userRepository.save(user);

        return "redirect:/login"; 
    }



    // Validate password strength
    private boolean isPasswordStrong(String password) {
        // Example: At least 8 characters, 1 uppercase, 1 lowercase, and 1 digit
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }
}
