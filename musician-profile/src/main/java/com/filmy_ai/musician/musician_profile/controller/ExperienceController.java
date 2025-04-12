package com.filmy_ai.musician.musician_profile.controller;

import com.filmy_ai.musician.musician_profile.dto.ExperienceDTO;
import com.filmy_ai.musician.musician_profile.model.Experience;
import com.filmy_ai.musician.musician_profile.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musician/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;



    @PostMapping("create/{profileId}")
    public ResponseEntity<Long> addExperience(@PathVariable Long profileId, @RequestBody ExperienceDTO dto) {
        Experience exp = experienceService.addExperience(profileId, dto);
        return ResponseEntity.ok(exp.getId());
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody ExperienceDTO dto) {
        experienceService.updateExperience(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

}
