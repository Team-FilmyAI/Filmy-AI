package com.filmy_ai.musician.musician_profile.controller;


import com.filmy_ai.musician.musician_profile.dto.RecommendationDTO;
import com.filmy_ai.musician.musician_profile.model.RecommendationDetail;
import com.filmy_ai.musician.musician_profile.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musician/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;


    @PostMapping("/create/{profileId}")
    public ResponseEntity<Long> addRecommendation(
            @PathVariable Long profileId,
            @RequestBody RecommendationDTO dto) {
        RecommendationDetail saved = recommendationService.addRecommendation(profileId, dto);
        return ResponseEntity.ok(saved.getId());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}
