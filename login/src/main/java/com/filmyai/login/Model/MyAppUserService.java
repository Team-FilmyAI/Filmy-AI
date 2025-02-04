package com.filmyai.login.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyAppUserService implements UserDetailsService{
    
    // Service implementing UserDetailsService to load user details by email for authentication

    @Autowired
    private MyAppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyAppUser user = repository.findByEmail(email);

        // Check if user is null
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Build the UserDetails object if user exists
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword_hash())
                .build();

        
    }




    
}
