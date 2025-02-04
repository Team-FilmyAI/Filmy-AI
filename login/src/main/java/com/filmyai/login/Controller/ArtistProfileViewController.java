package com.filmyai.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistProfileViewController {

    @GetMapping("/profile")
    public String viewProfilePage() {
        return "profile";  // This will return profile.html from src/main/resources/templates
    }

    @GetMapping("/success")
    public String viewSuccessPage() {
        return "success";  // This will return success.html from src/main/resources/templates
    }
}

