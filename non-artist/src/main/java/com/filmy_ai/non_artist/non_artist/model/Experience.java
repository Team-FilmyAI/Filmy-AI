package com.filmy_ai.non_artist.non_artist.model;

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

    @NotBlank(message = "Title of the Film")
    private String flimtitle;

    @NotBlank(message = "director name field cannot be empty")
    private String directorname;

    @NotBlank(message = "Producer name is required")
    private String producername;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Month is required")
    private String month;

    @NotBlank(message = "Year is required")
    private String year;

    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private NonArtistProfile NonArtistProfile;
}
