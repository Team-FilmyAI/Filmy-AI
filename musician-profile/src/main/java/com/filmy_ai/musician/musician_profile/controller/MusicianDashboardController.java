package com.filmy_ai.musician.musician_profile.controller;


import com.filmy_ai.musician.musician_profile.dto.MusicianDashboardDTO;
import com.filmy_ai.musician.musician_profile.service.MusicianProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musician")
public class MusicianDashboardController {

    @Autowired
    private MusicianProfileService profileService;

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<MusicianDashboardDTO> getDashboard(@PathVariable Long id) {
        MusicianDashboardDTO dto = profileService.getDashboard(id);
        return ResponseEntity.ok(dto);
    }
}
