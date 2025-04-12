package com.filmy_ai.non_artist.non_artist.controller;

import com.filmy_ai.non_artist.non_artist.dto.NonArtistDashboardDTO;
import com.filmy_ai.non_artist.non_artist.service.NonArtistProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nonartist")
public class NonArtistDashboardController {

    @Autowired
    private NonArtistProfileService nonArtistProfileService;

    @GetMapping(value = "/dashboard/{id}")
    public ResponseEntity<NonArtistDashboardDTO> getDashboard(@PathVariable Long id) {
        NonArtistDashboardDTO dto = nonArtistProfileService.getDashboard(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
