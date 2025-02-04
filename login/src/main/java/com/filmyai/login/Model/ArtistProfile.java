package com.filmyai.login.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "artist_profile")
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "userId", nullable = false)
    private Long userId; // Foreign key linking to the users table

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

    public ArtistProfile() {
    }

    // Constructor to initialize all fields except profileId
    public ArtistProfile(Long userId, String firstName, String lastName, String email, String contact, 
                         String occupation, String location, String profilePicturePath, String portfolioLink) {
        this.userId = userId; // Assigning userId
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.occupation = occupation;
        this.location = location;
        this.profilePicturePath = profilePicturePath;
        this.portfolioLink = portfolioLink;
    }

    // Getters and Setters

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
