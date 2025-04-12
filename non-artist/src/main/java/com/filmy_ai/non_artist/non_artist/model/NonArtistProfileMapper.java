package com.filmy_ai.non_artist.non_artist.dto;

import com.filmy_ai.non_artist.non_artist.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NonArtistProfileMapper {


    private NonAristProfileInformationDTO mapPerformanceInfo(NonArtistProfileInformation performanceInfo) {
        NonAristProfileInformationDTO dto = new NonAristProfileInformationDTO();
        dto.setStyle(performanceInfo.getStyle());
        dto.setExperience(performanceInfo.getExperience());
        dto.setEthnicity(Ethnicity.valueOf(String.valueOf(performanceInfo.getEthnicity())));
        dto.setSpecialty(performanceInfo.getSpecialty());
        dto.setThemes(performanceInfo.getThemes());
        dto.setAgeRange(AgeRange.valueOf(String.valueOf(performanceInfo.getAgeRange())));
        return dto;
    }



    private ExperienceDTO mapExperience(Experience experience) {
        ExperienceDTO dto = new ExperienceDTO();
        dto.setFlimtitle(experience.getFlimtitle());
        dto.setDirectorname(experience.getDirectorname());
        dto.setProducername(experience.getProducername());
        dto.setGenre(experience.getGenre());
        dto.setMonth(experience.getMonth());
        dto.setYear(experience.getYear());
        dto.setPosterUrl(experience.getPosterUrl());
        return dto;
    }

    private SlotBookingDTO mapSlot(SlotBooking s) {
        SlotBookingDTO dto = new SlotBookingDTO();
        dto.setDate(s.getDate());
        dto.setStatus(s.getStatus());
        dto.setMessage(s.getMessage());
        return dto;
    }


    public NonArtistDashboardDTO toDTO(NonArtistProfile profile) {
        NonArtistDashboardDTO dto = new NonArtistDashboardDTO();
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setEmail(profile.getEmail());
        dto.setLocation(profile.getLocation());
        dto.setPortfolioLink(profile.getPortfolioLink());
        dto.setBio(profile.getBio());
        dto.setAbout_me(profile.getAbout_me());
        dto.setProfileType(profile.getProfileType());
        dto.setProfilePicturePath(profile.getProfilePicturePath());


        if (profile.getNonartistProfileInformation() != null) {
            dto.setNonAristProfileInformationDTO(mapPerformanceInfo(profile.getNonartistProfileInformation()));
        }

        dto.setExperiences(profile.getExperiences().stream()
                .map(this::mapExperience).collect(Collectors.toList()));

        dto.setSlotBookings(profile.getSlotBookings().stream()
                .map(this::mapSlot).collect(Collectors.toList()));

        return dto;
    }


}
