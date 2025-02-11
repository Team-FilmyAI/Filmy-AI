package com.filmyai.login.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "non_artist_profile")
public class NonArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "non_artist_profile_id")
    private Long nonartistprofileId;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private MyAppUser myAppUser;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "occupation", nullable = false)
    private String occupation;

    @Column(name = "profile_picture_path")
    private String profilePicturePath; // Path to the uploaded profile picture

    @Column(name = "portfolio_link")
    private String portfolioLink;

    public Long getNonartistprofileId() {
        return nonartistprofileId;
    }

    public void setNonartistprofileId(Long nonartistprofileId) {
        this.nonartistprofileId = nonartistprofileId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public MyAppUser getMyAppUser() {
        return myAppUser;
    }

    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }

    

}
