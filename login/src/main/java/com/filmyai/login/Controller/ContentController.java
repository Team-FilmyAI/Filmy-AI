package com.filmyai.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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



        @GetMapping("/reset-password")
        public String resetPasswordPage(@RequestParam("token") String token, Model model) {
            // Pass the token to the view (reset-password.html)
            model.addAttribute("token", token);
            return "resetpassword"; // This maps to reset-password.html in templates
        }

}

