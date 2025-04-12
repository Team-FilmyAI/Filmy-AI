package com.filmy_ai.non_artist.non_artist.model;

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
public class NonArtistProfile {

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

    @Column(length = 100)
    private String bio;

    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "profile_picture_path")
    private String profilePicturePath;

    @Column(length = 500)
    private String about_me;

    @OneToOne(mappedBy = "NonArtistProfile", cascade = CascadeType.ALL)
    private NonArtistProfileInformation nonartistProfileInformation;

    @OneToMany(mappedBy = "NonArtistProfile", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "NonArtistProfile", cascade = CascadeType.ALL)
    private List<SlotBooking> slotBookings;

}
