package com.filmy_ai.non_artist.non_artist.service;

import com.filmy_ai.non_artist.non_artist.dto.ExperienceDTO;
import com.filmy_ai.non_artist.non_artist.exception.ResourceNotFoundException;
import com.filmy_ai.non_artist.non_artist.model.Experience;
import com.filmy_ai.non_artist.non_artist.model.NonArtistProfile;
import com.filmy_ai.non_artist.non_artist.repository.ExperienceRepository;
import com.filmy_ai.non_artist.non_artist.repository.NonArtistProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final NonArtistProfileRepo nonArtistProfileRepo;
    private final ExperienceRepository experienceRepository;

    public ExperienceService(NonArtistProfileRepo nonArtistProfileRepo, ExperienceRepository experienceRepository) {
        this.nonArtistProfileRepo = nonArtistProfileRepo;
        this.experienceRepository = experienceRepository;
    }

    private void updateEntityFromDTO(Experience exp, ExperienceDTO dto) {
        exp.setDirectorname(dto.getDirectorname());
        exp.setDirectorname(dto.getDirectorname());
        exp.setProducername(dto.getProducername());
        exp.setGenre(dto.getGenre());
        exp.setMonth(dto.getMonth());
        exp.setYear(dto.getYear());
        exp.setPosterUrl(dto.getPosterUrl());
    }


    public Experience addExperience(Long profileId, ExperienceDTO dto) {
        NonArtistProfile profile = nonArtistProfileRepo.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        Experience exp = new Experience();
        exp.setNonArtistProfile(profile);
        updateEntityFromDTO(exp, dto);
        return experienceRepository.save(exp);
    }

    public Experience updateExperience(Long id, ExperienceDTO dto) {
        Experience exp = experienceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Experience not found"));
        updateEntityFromDTO(exp, dto);
        return experienceRepository.save(exp);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

}
