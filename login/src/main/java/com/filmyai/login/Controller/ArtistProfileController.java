package com.filmyai.login.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.filmyai.login.Model.ArtistProfile;
import com.filmyai.login.Model.ArtistProfileRepository;
import com.filmyai.login.Model.ArtistProfileService;
import com.filmyai.login.Model.Experience;
import com.filmyai.login.Model.ExperienceRepository;
import com.filmyai.login.Model.ExperienceService;
import com.filmyai.login.Model.MyAppUser;
import com.filmyai.login.Model.MyAppUserRepository;

@Controller
@RequestMapping("/artist-profile")
public class ArtistProfileController {

    @Autowired
    private ArtistProfileService artistProfileService;

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private ArtistProfileRepository artistProfileRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceService experienceService;

    // Method to get the logged-in user's email and fetch user details by email
    private MyAppUser getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername(); // Assuming email is used as username

        // Fetch user using the email (Optional)
        MyAppUser user = myAppUserRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user; // Return the userId
    }

    @GetMapping("/profileDetails")
    public String profileDetails(Model model) {

        ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(getLoggedInUser());

        if (artistProfile == null) {
            return "redirect:/artist-profile";
        } else {
            model.addAttribute("profile", artistProfile);
            List<Experience> experiences = experienceRepository.findByArtistProfile(artistProfile);

            if (!experiences.isEmpty()) {
                model.addAttribute("experiences", experiences);
            }

            return "artist"; // changed

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

            MyAppUser user = getLoggedInUser();

            String profilePicturePath = artistProfileService.saveProfilePicture(profilePicture);

            ArtistProfile artistProfile = new ArtistProfile();
            artistProfile.setMyAppUser(user);
            artistProfile.setFirstName(firstName);
            artistProfile.setLastName(lastName);
            artistProfile.setEmail(email);
            artistProfile.setContact(contact);
            artistProfile.setOccupation(occupation);
            artistProfile.setLocation(location);
            artistProfile.setProfilePicturePath(profilePicturePath);
            artistProfile.setPortfolioLink(portfolioLink);
            artistProfile.setBio("Bio");
            artistProfile.setProfile_visibility("Public");
            artistProfile.setAbout("About....");
            artistProfile.setHeight(height + " " + heightUnit);
            artistProfile.setWeight(weight + " " + weightUnit);
            artistProfile.setEthnicity(ethnicity);
            artistProfile.setEyeColor(eyeColor);
            artistProfile.setHairColor(hairColor);
            artistProfile.setAgeRange(ageRange);

            artistProfileService.saveArtistProfile(artistProfile);
            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (Exception e) {

            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to create profile. Please try again.");
            return "redirect:/artist-profile"; // Redirect back to the profile page with an error
        }

    }

    @PostMapping("/editBasicDetails")
    @Transactional
    public String editBasicDetails(@RequestParam(value = "fname", required = false) String firstName,
            @RequestParam(value = "lname", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "profile-photo", required = false) MultipartFile profilePicture,
            @RequestParam(value = "portfolio", required = false) String portfolioLink,
            @RequestParam(value = "bio", required = false) String bio,
            RedirectAttributes redirectAttributes) {

        try {

            MyAppUser user = getLoggedInUser();

            ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(user); // fetch existing profile

            // Only update fields that are not null or empty
            if (firstName != null && !firstName.trim().isEmpty()) {
                artistProfile.setFirstName(firstName);
            }
            if (lastName != null && !lastName.trim().isEmpty()) {
                artistProfile.setLastName(lastName);
            }
            if (email != null && !email.trim().isEmpty()) {
                artistProfile.setEmail(email);
            }
            if (location != null && !location.trim().isEmpty()) {
                artistProfile.setLocation(location);
            }
            if (portfolioLink != null && !portfolioLink.trim().isEmpty()) {
                artistProfile.setPortfolioLink(portfolioLink);
            }
            if (bio != null && !bio.trim().isEmpty()) {
                artistProfile.setBio(bio);
            }

            // Handle profile picture update only if a new file is provided
            if (profilePicture != null && !profilePicture.isEmpty()) {
                String profilePicturePath = artistProfileService.saveProfilePicture(profilePicture);
                artistProfile.setProfilePicturePath(profilePicturePath);
            }

            artistProfileService.saveArtistProfile(artistProfile);

            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (Exception e) {

            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to edit profile. Please try again.");
            return "redirect:/artist-profile/profileDetails"; // Redirect back to the profile page with an error
        }

    }

    @PostMapping("/update-profile-visibility")
    public String updateProfileVisibility(@RequestParam("visibility") String visibility,
            RedirectAttributes redirectAttributes) {
        try {

            MyAppUser user = getLoggedInUser();
            ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(user); // fetch existing profile
            artistProfile.setProfile_visibility(visibility);
            artistProfileService.saveArtistProfile(artistProfile);

            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (Exception e) {

            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to edit profile. Please try again.");
            return "redirect:/artist-profile/profileDetails"; // Redirect back to the profile page with an error
        }

    }

    @PostMapping("/editAbout")
    @Transactional
    public String editAbout(@RequestParam(value = "about") String about,
            RedirectAttributes redirectAttributes) {

        try {

            MyAppUser user = getLoggedInUser();
            ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(user); // fetch existing profile

            if (about != null && !about.trim().isEmpty()) {
                artistProfile.setAbout(about);
            }

            artistProfileService.saveArtistProfile(artistProfile);

            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (Exception e) {

            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to edit profile. Please try again.");
            return "redirect:/artist-profile/profileDetails"; // Redirect back to the profile page with an error
        }

    }

    @PostMapping("/editPhysicalInformation")
    @Transactional
    public String editPhysicalInformation(@RequestParam(value = "height", required = false) String height,
            @RequestParam(value = "weight", required = false) String weight,
            @RequestParam(value = "ethnicity", required = false) String ethnicity,
            @RequestParam(value = "hair-color", required = false) String hairColor,
            @RequestParam(value = "eye-color", required = false) String eyeColor,
            @RequestParam(value = "age-range", required = false) String ageRange,
            RedirectAttributes redirectAttributes) {
        try {

            MyAppUser user = getLoggedInUser();

            ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(user); // fetch existing profile

            // Only update fields that are not null or empty
            if (height != null && !height.trim().isEmpty()) {
                artistProfile.setHeight(height);
            }
            if (weight != null && !weight.trim().isEmpty()) {
                artistProfile.setWeight(weight);
            }
            if (ethnicity != null && !ethnicity.trim().isEmpty()) {
                artistProfile.setEthnicity(ethnicity);
            }
            if (hairColor != null && !hairColor.trim().isEmpty()) {
                artistProfile.setHairColor(hairColor);
            }
            if (eyeColor != null && !eyeColor.trim().isEmpty()) {
                artistProfile.setEyeColor(eyeColor);
            }
            if (ageRange != null && !ageRange.trim().isEmpty()) {
                artistProfile.setAgeRange(ageRange);
            }

            artistProfileService.saveArtistProfile(artistProfile);

            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (Exception e) {

            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to edit profile. Please try again.");
            return "redirect:/artist-profile/profileDetails"; // Redirect back to the profile page with an error
        }

    }

    @PostMapping("/addExperience")
    @Transactional
    public String addExperience(@RequestParam(value = "movie-title") String movieTitle,
            @RequestParam(value = "character-name") String characterName,
            @RequestParam(value = "genre") String genre,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "movie-poster") MultipartFile moviePoster,
            @RequestParam(value = "year") String year,
            RedirectAttributes redirectAttributes) {

        try {

            MyAppUser user = getLoggedInUser();

            ArtistProfile artistProfile = artistProfileRepository.findByMyAppUser(user); // fetch existing profile
            Experience experience = new Experience();

            experience.setArtistProfile(artistProfile);

            if (movieTitle == null || movieTitle.trim().isEmpty() ||
                    characterName == null || characterName.trim().isEmpty() ||
                    genre == null || genre.trim().isEmpty() ||
                    month == null || month.trim().isEmpty() ||
                    year == null || year.trim().isEmpty() ||
                    moviePoster == null || moviePoster.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            // Only update fields that are not null or empty
            experience.setMovieTitle(movieTitle);
            experience.setCharacterName(characterName);
            experience.setGenre(genre);
            experience.setMonth(month);
            experience.setYear(year);

            String moviePosterPath = experienceService.saveMoviePoster(moviePoster);
            experience.setMoviePoster(moviePosterPath);

            experienceService.saveExperience(experience);

            return "redirect:/artist-profile/profileDetails"; // Redirect to the profile page

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/artist-profile/profileDetails";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
            return "redirect:/artist-profile/profileDetails";
        }

    }

    @PostMapping("/editExperience/{experienceId}")
    @Transactional
    public String editExperience(@PathVariable Long experienceId,
            @RequestParam(value = "movie-title", required=false) String movieTitle,
            @RequestParam(value = "character-name", required=false) String characterName,
            @RequestParam(value = "genre", required=false) String genre,
            @RequestParam(value = "month", required=false) String month,
            @RequestParam(value = "year", required=false) String year,
            @RequestParam(value = "movie-poster", required = false) MultipartFile moviePoster,
            RedirectAttributes redirectAttributes) {

        try {
            
            
            Experience experience = experienceRepository.findByExperienceId(experienceId);

            if (movieTitle != null && !movieTitle.trim().isEmpty()) {
                experience.setMovieTitle(movieTitle);
            }
            if (characterName != null && !characterName.trim().isEmpty()) {
                experience.setCharacterName(characterName);
            }
            if (genre != null && !genre.trim().isEmpty()) {
                experience.setGenre(genre);
            }
            if (month != null && !month.trim().isEmpty()) {
                experience.setMonth(month);
            }
            if (year != null && !year.trim().isEmpty()) {
                experience.setYear(year);
            }

            if (moviePoster != null && !moviePoster.isEmpty()) {
                String moviePosterPath = experienceService.saveMoviePoster(moviePoster);
                experience.setMoviePoster(moviePosterPath);
            }

            experienceService.saveExperience(experience);

            return "redirect:/artist-profile/profileDetails";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Failed to update experience.");
            return "redirect:/artist-profile/profileDetails";
        }
    }

}
