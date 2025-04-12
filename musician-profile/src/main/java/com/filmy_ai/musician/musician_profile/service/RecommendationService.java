package com.filmy_ai.musician.musician_profile.service;

import com.filmy_ai.musician.musician_profile.dto.RecommendationDTO;
import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.model.RecommendationDetail;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import com.filmy_ai.musician.musician_profile.repository.RecommendationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationDetailRepository recommendationRepo;

    @Autowired
    private MusicianProfileRepository profileRepo;

    public RecommendationDetail addRecommendation(Long profileId, RecommendationDTO dto) {
        MusicianProfile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        RecommendationDetail recommendation = new RecommendationDetail();
        recommendation.setOrganizationName(dto.getOrganizationName());
        recommendation.setWorkedAt(dto.getWorkedAt());
        recommendation.setRecommendationText(dto.getRecommendationText());
        recommendation.setRecommendationDate(dto.getRecommendationDate());
        recommendation.setVisible(dto.getVisible());
        recommendation.setProfile(profile);

        return recommendationRepo.save(recommendation);
    }

    public void deleteRecommendation(Long id) {
        recommendationRepo.deleteById(id);
    }
}
