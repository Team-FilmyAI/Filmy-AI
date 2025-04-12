package com.filmy_ai.musician.musician_profile.dto;

import com.filmy_ai.musician.musician_profile.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MusicianProfileMapper {



    private MusicianPerformanceDTO mapPerformanceInfo(MusicianPerformanceInfo performanceInfo) {
        MusicianPerformanceDTO dto = new MusicianPerformanceDTO();
        dto.setInstrumentPlayed(performanceInfo.getInstrumentPlayed());
        dto.setVocalRange(performanceInfo.getVocalRange());
        dto.setEthnicity(performanceInfo.getEthnicity());
        dto.setStagePresence(performanceInfo.getStagePresence());
        dto.setAgeRange(performanceInfo.getAgeRange());
        return dto;
    }

    private ExperienceDTO mapExperience(Experience experience) {
        ExperienceDTO dto = new ExperienceDTO();
        dto.setSongTitle(experience.getSongTitle());
        dto.setSingers(experience.getSingers());
        dto.setGenre(experience.getGenre());
        dto.setMonth(experience.getMonth());
        dto.setYear(experience.getYear());
        dto.setPosterUrl(experience.getPosterUrl());
        return dto;
    }

    private RecommendationDTO mapRecommendation(RecommendationDetail r) {
        RecommendationDTO dto = new RecommendationDTO();
        dto.setOrganizationName(r.getOrganizationName());
        dto.setWorkedAt(r.getWorkedAt());
        dto.setRecommendationText(r.getRecommendationText());
        dto.setRecommendationDate(r.getRecommendationDate());
        dto.setVisible(r.getVisible());
        return dto;
    }

    private SlotBookingDTO mapSlot(SlotBooking s) {
        SlotBookingDTO dto = new SlotBookingDTO();
        dto.setDate(s.getDate());
        dto.setStatus(s.getStatus());
        dto.setMessage(s.getMessage());
        return dto;
    }


    public MusicianDashboardDTO toDTO(MusicianProfile profile) {
        MusicianDashboardDTO dto = new MusicianDashboardDTO();
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setEmail(profile.getEmail());
        dto.setLocation(profile.getLocation());
        dto.setPortfolioLink(profile.getPortfolioLink());
        dto.setBio(profile.getBio());
        dto.setProfileType(profile.getProfileType());
        dto.setProfilePicturePath(profile.getProfilePicturePath());


        if (profile.getPerformanceInfo() != null) {
            dto.setPerformanceInfo(mapPerformanceInfo(profile.getPerformanceInfo()));
        }

        dto.setExperiences(profile.getExperiences().stream()
                .map(this::mapExperience).collect(Collectors.toList()));

        dto.setRecommendations(profile.getRecommendationDetail().stream()
                .map(this::mapRecommendation).collect(Collectors.toList()));

        dto.setSlotBookings(profile.getSlotBookings().stream()
                .map(this::mapSlot).collect(Collectors.toList()));

        return dto;
    }

}
