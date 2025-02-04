package com.filmyai.login.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.filmyai.login.Model.Actor;
import com.filmyai.login.Model.ActorRepository;
import com.filmyai.login.Model.ArtistProfile;
import com.filmyai.login.Model.ArtistProfileRepository;
import com.filmyai.login.Model.ArtistProfileService;
import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;

import org.springframework.ui.Model;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/profile")
public class ArtistProfileController {

    @Autowired
    private ArtistProfileService artistProfileService;

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private ArtistProfileRepository artistProfileRepository; 

    @Autowired
    private ActorRepository actorRepository;


    // Method to get the logged-in user's email and fetch user details by email
    private Long getLoggedInUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();  // Assuming email is used as username

        // Fetch user using the email (Optional)
        MyAppUser user = myAppUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getUser_id();  // Return the userId
    }




    @GetMapping("/profileDetails")
    public String profileDetails(Model model) {

        Long userId = getLoggedInUserId();
        ArtistProfile profile = artistProfileRepository.findByUserId(userId);
        
        if (profile == null) {
            return "redirect:/profile";
        }

        else{
            Actor actor =  actorRepository.findByArtistProfile_ProfileId(profile.getProfileId());    
    
    
            model.addAttribute("profile", profile);
            model.addAttribute("actor", actor);
            return "success";
                    
        }

    }
 
    @PostMapping("/create")
    @Transactional
    public String createArtistProfile(@RequestParam("fname") String firstName,
                                                  @RequestParam("lname") String lastName,
                                                  @RequestParam("email") String email,
                                                  @RequestParam("contact") String contact,
                                                  @RequestParam("occupation") String occupation,
                                                  @RequestParam("location") String location,
                                                  @RequestParam("profile-photo") MultipartFile profilePicture,
                                                  @RequestParam("portfolio") String portfolioLink,
                                                  @RequestParam(value = "height", required = false) BigDecimal height,
                                                  @RequestParam(value = "weight", required = false) BigDecimal weight,
                                                  @RequestParam(value = "eye-color", required = false) String eyeColor,
                                                  @RequestParam(value = "hair-color", required = false) String hairColor,
                                                  @RequestParam(value = "age-range", required = false) String ageRange,
                                                  @RequestParam(value = "ethnicity", required = false) String ethnicity,
                                                  @RequestParam(value = "height-unit", required = false) String heightUnit,
                                                  @RequestParam(value = "weight-unit", required = false) String weightUnit,
                                                  RedirectAttributes redirectAttributes) {


                                                    
        try {
            Long userId = getLoggedInUserId();

            String profilePicturePath = artistProfileService.saveProfilePicture(profilePicture);

            ArtistProfile artistProfile = new ArtistProfile(userId, firstName, lastName, email, contact,
                                                            occupation, location, profilePicturePath, portfolioLink);
            artistProfileService.saveArtistProfile(artistProfile);
            
        // If occupation is 'actor' or 'actress', create an entry in the Actor table
        if ("actor".equalsIgnoreCase(occupation) || "actress".equalsIgnoreCase(occupation)) {
            Actor actor = new Actor();
            actor.setArtistProfile(artistProfile); 

            actor.setHeightValue(height);
            actor.setWeightValue(weight); 
            actor.setHeightUnit(heightUnit);
            actor.setWeightUnit(weightUnit);
            actor.setEyeColor(eyeColor);
            actor.setHairColor(hairColor); 
            actor.setAgeRange(ageRange); 
            actor.setEthnicity(ethnicity);

            // Save the Actor entry in the Actor table
            actorRepository.save(actor);
        }

            return "redirect:/profile/profileDetails"; // Redirect to the success page

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to create profile. Please try again.");
            return "redirect:/profile"; // Redirect back to the profile page with an error
        }

    }





}
