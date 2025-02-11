package com.filmyai.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;
import com.filmyai.login.Model.NonArtistProfile;
import com.filmyai.login.Model.NonArtistProfileRepository;
import com.filmyai.login.Model.NonArtistProfileService;

import org.springframework.ui.Model;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/non-artist-profile")
public class NonArtistProfileController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private NonArtistProfileService nonArtistProfileService;

    @Autowired
    private NonArtistProfileRepository nonArtistProfileRepository;



    // Method to get the logged-in user's email and fetch user details by email
    private MyAppUser getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();  // Assuming email is used as username

        // Fetch user using the email (Optional)
        MyAppUser user = myAppUserRepository.findByEmail(email);

        if(user==null){
            throw new RuntimeException("User not found");
        }

        return user;  // Return the user
    }




    @GetMapping("/profileDetails")
    public String profileDetails(Model model) {

        NonArtistProfile nonArtistProfile = nonArtistProfileRepository.findByMyAppUser(getLoggedInUser());

        
        if (nonArtistProfile == null) {
            return "redirect:/non-artist-profile";
        }

        else{
            
            model.addAttribute("profile", nonArtistProfile);
            return "success";
                    
        }

    }
 
    @PostMapping("/create")
    @Transactional
    public String createNonArtistProfile(@RequestParam("non-fname") String firstName,
                                                  @RequestParam("non-lname") String lastName,
                                                  @RequestParam("non-email") String email,
                                                  @RequestParam("non-contact") String contact,
                                                  @RequestParam("non-occupation") String occupation,
                                                  @RequestParam("non-location") String location,
                                                  @RequestParam("non-profile-photo") MultipartFile profilePicture,
                                                  @RequestParam("non-portfolio") String portfolioLink,
                                                  RedirectAttributes redirectAttributes) {

                                                    
        try {
            
            MyAppUser user = getLoggedInUser();

            String profilePicturePath = nonArtistProfileService.saveProfilePicture(profilePicture);

            NonArtistProfile nonArtistProfile = new NonArtistProfile();
            nonArtistProfile.setMyAppUser(user);
            nonArtistProfile.setFirstName(firstName);
            nonArtistProfile.setLastName(lastName);
            nonArtistProfile.setEmail(email);
            nonArtistProfile.setContact(contact);
            nonArtistProfile.setOccupation(occupation);
            nonArtistProfile.setLocation(location);
            nonArtistProfile.setProfilePicturePath(profilePicturePath);
            nonArtistProfile.setPortfolioLink(portfolioLink);

            
            nonArtistProfileService.saveNonArtistProfile(nonArtistProfile);

            return "redirect:/non-artist-profile/profileDetails"; // Redirect to the success page

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to create profile. Please try again.");
            return "redirect:/non-artist-profile"; // Redirect back to the profile page with an error
        }

    }


}
