package com.filmy_ai.musician.musician_profile.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class MusicianProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    private String location;
    private String portfolioLink;

    @Column(length = 500)
    private String bio;

    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "profile_picture_path")
    private String profilePicturePath;


    // Relationships
    // will do it later after creating other tables

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private MusicianPerformanceInfo performanceInfo;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<RecommendationDetail> recommendationDetail;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<SlotBooking> slotBookings;


}
