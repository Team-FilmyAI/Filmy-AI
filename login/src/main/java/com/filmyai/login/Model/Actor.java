package com.filmyai.login.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Actor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "artist_profile_id")
    private ArtistProfile artistProfile;

    private BigDecimal heightValue;
    private String heightUnit;

    private BigDecimal weightValue;
    private String weightUnit;

    private String eyeColor;
    private String hairColor;
    private String ageRange;
    private String ethnicity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistProfile getArtistProfile() {
        return artistProfile;
    }

    public void setArtistProfile(ArtistProfile artistProfile) {
        this.artistProfile = artistProfile;
    }

    public BigDecimal getHeightValue() {
        return heightValue;
    }

    public void setHeightValue(BigDecimal heightValue) {
        this.heightValue = heightValue;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public BigDecimal getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(BigDecimal weightValue) {
        this.weightValue = weightValue;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

   

    

    
}
