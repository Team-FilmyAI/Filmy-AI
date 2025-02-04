package com.filmyai.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    
    // This ContentController maps URL paths to their respective view names for login-related pages. 
    // It returns the name of the html file.


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/forgot")
    public String forgot(){
        return "forgot";
    }

    @GetMapping("/profile")
    public String profileForm() {
        return "profile";  // This will return profile.html from src/main/resources/templates
    }

    @GetMapping("/artist-profile")
    public String viewArtistProfile() {
        return "profile";  // This will return profile.html from src/main/resources/templates
    }


    @GetMapping("/non-artist-profile")
    public String viewNonArtistProfile() {
        return "profile";  // This will return profile.html from src/main/resources/templates
    }

    @GetMapping("/success")
    public String viewSuccessPage() {
        return "success";  // This will return success.html from src/main/resources/templates
    }
}

