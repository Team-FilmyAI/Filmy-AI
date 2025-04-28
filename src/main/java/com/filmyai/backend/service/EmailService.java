package com.filmyai.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Email Service for sending mails
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
