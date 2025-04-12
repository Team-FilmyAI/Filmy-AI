package com.filmy_ai.non_artist.non_artist.controller;



import com.filmy_ai.non_artist.non_artist.dto.NonAristProfileInformationDTO;
import com.filmy_ai.non_artist.non_artist.dto.NonArtistBasicInfoDTO;
import com.filmy_ai.non_artist.non_artist.model.NonArtistProfile;
import com.filmy_ai.non_artist.non_artist.service.NonArtistProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/nonartist/profile")
public class NonArtistProfileEditController {

    @Autowired
    private NonArtistProfileService nonArtistProfileService;


    @PostMapping()
    public ResponseEntity<Long> createNonArtist(@RequestBody NonArtistBasicInfoDTO dto) {
        NonArtistProfile profile = nonArtistProfileService.createNonArtist(dto);
        return ResponseEntity.ok(profile.getId());
    }

    @PutMapping("/{id}/basic")
    public ResponseEntity<Void> updateBasic(@PathVariable Long id, @RequestBody NonArtistBasicInfoDTO dto) {
        nonArtistProfileService.updateBasicInfo(id, dto);
        return ResponseEntity.ok().build();
    }

    // Acts as both POST and Put request
    @PutMapping("/{id}/performance")
    public ResponseEntity<Void> updatePerformance(@PathVariable Long id, @RequestBody NonAristProfileInformationDTO dto) {
        nonArtistProfileService.updatePerformanceInfo(id, dto);
        return ResponseEntity.ok().build();
    }

}
