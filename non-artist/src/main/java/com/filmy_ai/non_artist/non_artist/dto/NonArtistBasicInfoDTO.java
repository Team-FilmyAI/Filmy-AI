package com.filmy_ai.non_artist.non_artist.dto;

import com.filmy_ai.non_artist.non_artist.model.ProfileType;
import lombok.Data;

@Data
public class NonArtistBasicInfoDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String portfolioLink;
    private String bio;
    private ProfileType profileType;
    private String about_me;
}
