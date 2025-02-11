package com.filmyai.login.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.filmyai.login.Model.ArtistProfileRepository;
import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;
import com.filmyai.login.Model.NonArtistProfileRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    
    @Autowired
    MyAppUserRepository myAppUserRepository; 

    @Autowired
    ArtistProfileRepository artistProfileRepository;

    @Autowired
    NonArtistProfileRepository nonArtistProfileRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                        HttpServletResponse response, 
                                        Authentication authentication) throws IOException {

    String email = authentication.getName(); // Get logged-in user's email



    // Fetch user from database
    MyAppUser user = myAppUserRepository.findByEmail(email);

    if (user != null) {
        if (artistProfileRepository.findByMyAppUser(user) != null) {
            response.sendRedirect("/artist-profile/profileDetails");
            return;
        }
        if (nonArtistProfileRepository.findByMyAppUser(user) != null) {
            response.sendRedirect("/non-artist-profile/profileDetails");
            return;
        }
    }

    response.sendRedirect("/profile"); // Default fallback
}

}
