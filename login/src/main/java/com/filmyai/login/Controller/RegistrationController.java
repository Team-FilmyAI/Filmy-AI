package com.filmyai.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;

@RestController
public class RegistrationController {

    // The RegistrationController hashes the user's
    // password and saves the user data to the database when a POST request is made to the "/signup" endpoint.

    @Autowired
    private MyAppUserRepository myAppUserRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping(value = "/signup", consumes = "application/json")
    public MyAppUser createUser(@RequestBody MyAppUser user){
        user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));
        return myAppUserRepository.save(user);
    }
    
}
