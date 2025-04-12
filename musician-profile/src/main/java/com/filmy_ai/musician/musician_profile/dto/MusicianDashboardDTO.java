package com.filmy_ai.musician.musician_profile.dto;

import com.filmy_ai.musician.musician_profile.model.ProfileType;
import lombok.Data;

import java.util.List;

@Data
public class MusicianDashboardDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String portfolioLink;
    private String bio;
    private ProfileType profileType;
    private String profilePicturePath;


    private MusicianPerformanceDTO performanceInfo;

    private List<ExperienceDTO> experiences;
    private List<RecommendationDTO> recommendations;
    private List<SlotBookingDTO> slotBookings;
}
