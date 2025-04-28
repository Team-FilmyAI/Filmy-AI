package com.filmyai.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.model.DemoRequest;
import com.filmyai.backend.service.DemoRequestService;
import com.filmyai.backend.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class DemoRequestController {

	@Autowired
	private DemoRequestService demoRequestService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/index")
	public String handleDemoRequest(@RequestBody DemoRequest demoRequest) {
		
		demoRequestService.saveDemoRequest(demoRequest);
		String email = demoRequest.getEmail();
		//send email notification 
		
		String subject = "Demo Request Succussfully Registered!";
		String body = "Hello " +demoRequest.getFirstName() +", \n\n" +"Your request for demo successfully registered." 
						+" Our team connect with you shortly." + "\n\n Regards, \n Team FilmyAI";
		emailService.sendMail(email, subject, body);
		
		
		return "Demo request submitted successfully!";
	}
	
	

}
