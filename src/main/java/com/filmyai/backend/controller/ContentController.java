package com.filmyai.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContentController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/forgot")
	public String forgot() {
		return "forgot";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping("/forget")
	public String forget() {
		return "forget";
	}
	
	@GetMapping("/resetpassword")
	public String resetpassword(@RequestParam("token") String token, Model model) {
		//Pass the token to the view(reset-password.html)
		model.addAttribute("token", token);		
		return "resetpassword";  //This map to reset-password.html in templates
	}
}
