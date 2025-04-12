package com.filmy_ai.musician.musician_profile.service;

import com.filmy_ai.musician.musician_profile.dto.*;
import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.MusicianPerformanceInfo;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.repository.MusicianPerformanceInfoRepository;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicianProfileService {
    @Autowired
    private MusicianProfileRepository profileRepo;

    @Autowired
    private MusicianPerformanceInfoRepository performanceRepo;

    @Autowired
    private MusicianProfileMapper mapper;

    public MusicianDashboardDTO getDashboard(Long id) {
        MusicianProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        return mapper.toDTO(profile);
    }

    public void updateBasicInfo(Long id, MusicianBasicInfoDTO dto) {
        MusicianProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setLocation(dto.getLocation());
        profile.setPortfolioLink(dto.getPortfolioLink());

        profileRepo.save(profile);
    }

    public void updateBio(Long id, MusicianAboutDTO dto) {
        MusicianProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profile.setBio(dto.getBio());
        profileRepo.save(profile);
    }

    public void updatePerformanceInfo(Long id, MusicianPerformanceDTO dto) {
        MusicianProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        MusicianPerformanceInfo perf = profile.getPerformanceInfo();
        if (perf == null) {
            perf = new MusicianPerformanceInfo();
            perf.setProfile(profile);
        }

        perf.setInstrumentPlayed(dto.getInstrumentPlayed());
        perf.setVocalRange(dto.getVocalRange());
        perf.setEthnicity(dto.getEthnicity());
        perf.setStagePresence(dto.getStagePresence());
        perf.setAgeRange(dto.getAgeRange());

        performanceRepo.save(perf);
    }

    public MusicianProfile createMusician(MusicianBasicInfoDTO dto) {
        MusicianProfile profile = new MusicianProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setLocation(dto.getLocation());
        profile.setPortfolioLink(dto.getPortfolioLink());

        return profileRepo.save(profile); // returns the saved object with generated ID
    }
}
