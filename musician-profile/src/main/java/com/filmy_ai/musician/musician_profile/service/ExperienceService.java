package com.filmy_ai.musician.musician_profile.service;

import com.filmy_ai.musician.musician_profile.dto.ExperienceDTO;
import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.Experience;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.repository.ExperienceRepository;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepo;
    private final MusicianProfileRepository profileRepo;

    public ExperienceService(ExperienceRepository experienceRepo, MusicianProfileRepository profileRepo) {
        this.experienceRepo = experienceRepo;
        this.profileRepo = profileRepo;
    }


    public Experience addExperience(Long profileId, ExperienceDTO dto) {
        MusicianProfile profile = profileRepo.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        Experience exp = new Experience();
        exp.setProfile(profile);
        updateEntityFromDTO(exp, dto);
        return experienceRepo.save(exp);
    }

    public Experience updateExperience(Long id, ExperienceDTO dto) {
        Experience exp = experienceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Experience not found"));
        updateEntityFromDTO(exp, dto);
        return experienceRepo.save(exp);
    }

    public void deleteExperience(Long id) {
        experienceRepo.deleteById(id);
    }

    private void updateEntityFromDTO(Experience exp, ExperienceDTO dto) {
        exp.setSongTitle(dto.getSongTitle());
        exp.setSingers(dto.getSingers());
        exp.setGenre(dto.getGenre());
        exp.setMonth(dto.getMonth());
        exp.setYear(dto.getYear());
        exp.setPosterUrl(dto.getPosterUrl());
    }
}
