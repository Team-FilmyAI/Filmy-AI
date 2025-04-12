package com.filmy_ai.musician.musician_profile.controller;

import com.filmy_ai.musician.musician_profile.dto.MusicianAboutDTO;
import com.filmy_ai.musician.musician_profile.dto.MusicianBasicInfoDTO;
import com.filmy_ai.musician.musician_profile.dto.MusicianPerformanceDTO;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.service.MusicianProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musician/profile")
public class MusicianProfileEditController {


    @Autowired
    private MusicianProfileService profileService;

    @PostMapping()
    public ResponseEntity<Long> createMusician(@RequestBody MusicianBasicInfoDTO dto) {
        MusicianProfile profile = profileService.createMusician(dto);
        return ResponseEntity.ok(profile.getId());
    }

    @PutMapping("/{id}/basic")
    public ResponseEntity<Void> updateBasic(@PathVariable Long id, @RequestBody MusicianBasicInfoDTO dto) {
        profileService.updateBasicInfo(id, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/about")
    public ResponseEntity<Void> updateBio(@PathVariable Long id, @RequestBody MusicianAboutDTO dto) {
        profileService.updateBio(id, dto);
        return ResponseEntity.ok().build();
    }

    // Acts as both POST and Put request
    @PutMapping("/{id}/performance")
    public ResponseEntity<Void> updatePerformance(@PathVariable Long id, @RequestBody MusicianPerformanceDTO dto) {
        profileService.updatePerformanceInfo(id, dto);
        return ResponseEntity.ok().build();
    }

}
