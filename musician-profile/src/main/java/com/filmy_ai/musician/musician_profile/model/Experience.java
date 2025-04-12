package com.filmy_ai.musician.musician_profile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Song Title is required")
    private String songTitle;

    @NotBlank(message = "Singers field cannot be empty")
    private String singers;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Month is required")
    private String month;

    @NotBlank(message = "Year is required")
    private String year;

    private String posterUrl; // Optional — so no validation here

    //Relationships
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private MusicianProfile profile;
}
