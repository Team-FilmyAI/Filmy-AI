package com.filmy_ai.non_artist.non_artist.service;

import com.filmy_ai.non_artist.non_artist.dto.NonAristProfileInformationDTO;
import com.filmy_ai.non_artist.non_artist.dto.NonArtistBasicInfoDTO;
import com.filmy_ai.non_artist.non_artist.dto.NonArtistDashboardDTO;
import com.filmy_ai.non_artist.non_artist.dto.NonArtistProfileMapper;
import com.filmy_ai.non_artist.non_artist.exception.ResourceNotFoundException;
import com.filmy_ai.non_artist.non_artist.model.*;
import com.filmy_ai.non_artist.non_artist.repository.NonArtistProfileInformationRepo;
import com.filmy_ai.non_artist.non_artist.repository.NonArtistProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonArtistProfileService {

    @Autowired
    private NonArtistProfileRepo nonArtistProfileRepo;

    @Autowired
    private NonArtistProfileInformationRepo nonArtistProfileInformationRepo;

    @Autowired
    private NonArtistProfileMapper mapper;



    public NonArtistDashboardDTO getDashboard(Long id) {
        NonArtistProfile profile = nonArtistProfileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        return mapper.toDTO(profile);
    }


    public NonArtistProfile createNonArtist(NonArtistBasicInfoDTO dto) {
        NonArtistProfile profile = new NonArtistProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setLocation(dto.getLocation());
        profile.setPortfolioLink(dto.getPortfolioLink());
        profile.setBio(dto.getBio());
        profile.setProfileType(dto.getProfileType());
        profile.setAbout_me(dto.getAbout_me());
        return nonArtistProfileRepo.save(profile);
    }

    public void updateBasicInfo(Long id, NonArtistBasicInfoDTO dto) {

        NonArtistProfile profile = nonArtistProfileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setLocation(dto.getLocation());
        profile.setPortfolioLink(dto.getPortfolioLink());
        profile.setBio(dto.getBio());
        profile.setProfileType(dto.getProfileType());
        profile.setAbout_me(dto.getAbout_me());
        nonArtistProfileRepo.save(profile);
    }

    public void updatePerformanceInfo(Long id, NonAristProfileInformationDTO dto) {
        NonArtistProfile profile = nonArtistProfileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        NonArtistProfileInformation profileInfo = profile.getNonartistProfileInformation();

        profileInfo.setStyle(dto.getStyle());
        profileInfo.setExperience(dto.getExperience());
        profileInfo.setEthnicity(Ethnicity.valueOf(dto.getEthnicity().toString()));
        profileInfo.setSpecialty(dto.getSpecialty());
        profileInfo.setThemes(dto.getThemes());
        profileInfo.setAgeRange(AgeRange.valueOf(dto.getAgeRange().toString()));

        nonArtistProfileInformationRepo.save(profileInfo);
    }
}
