package com.filmyai.login.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "artist_profile")
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_profile_id")
    private Long artistprofileId;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private MyAppUser myAppUser;

    @OneToMany(mappedBy = "artistProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "occupation", nullable = false)
    private String occupation;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "profile_picture_path")
    private String profilePicturePath; // Path to the uploaded profile picture

    @Column(name = "portfolio_link")
    private String portfolioLink;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_visibility")
    private String profile_visibility;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "ethnicity")
    private String ethnicity;    

    @Column(name = "HairColor")
    private String hairColor;

    @Column(name = "EyeColor")
    private String eyeColor;

    @Column(name = "AgeRange")
    private String ageRange;
    

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfile_visibility() {
        return profile_visibility;
    }

    public void setProfile_visibility(String profile_visibility) {
        this.profile_visibility = profile_visibility;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getArtistprofileId() {
        return artistprofileId;
    }

    public void setArtistprofileId(Long artistprofileId) {
        this.artistprofileId = artistprofileId;
    }

    public MyAppUser getMyAppUser() {
        return myAppUser;
    }

    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getPortfolioLink() {
        return portfolioLink;
    }

    public void setPortfolioLink(String portfolioLink) {
        this.portfolioLink = portfolioLink;
    }
}
