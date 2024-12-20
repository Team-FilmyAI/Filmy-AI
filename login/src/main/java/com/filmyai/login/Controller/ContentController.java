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

}

