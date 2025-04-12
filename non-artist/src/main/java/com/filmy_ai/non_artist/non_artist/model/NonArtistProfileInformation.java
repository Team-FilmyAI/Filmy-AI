package com.filmy_ai.non_artist.non_artist.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class NonArtistProfileInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String style;
    private String experience;

    @Enumerated(EnumType.STRING)
    private Ethnicity ethnicity;

    private String specialty;


    private String themes;

    @Enumerated(EnumType.STRING)
    private AgeRange AgeRange;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private NonArtistProfile NonArtistProfile;
}
