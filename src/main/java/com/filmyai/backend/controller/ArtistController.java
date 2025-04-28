package com.filmyai.backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.filmyai.backend.dto.ArtistCreationDto;
import com.filmyai.backend.model.Artist;
import com.filmyai.backend.repository.ArtistRepository;
import com.filmyai.backend.service.ArtistService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/api/auth/profile")
public class ArtistController {

	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private ArtistRepository artistRepository;

	@PostMapping(value = "/create-artist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createArtist(@RequestPart("artist-form-data") String artistCreationRequest,
			@RequestPart("profile-photo") MultipartFile profilePhoto) throws IOException {
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			ArtistCreationDto artistDTO = objectMapper.readValue(artistCreationRequest, ArtistCreationDto.class);

			Artist artist = artistService.createArtistProfile(artistDTO, profilePhoto);
			System.out.println(artist.getFirstName());

			return ResponseEntity.ok("Artist created successfully!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}


    @GetMapping("/view-all-artists")
    public ResponseEntity<?> viewAllArtists() {
        try {
            List<Artist> artists = artistRepository.findAll();

            if (artists.isEmpty()) {
                return ResponseEntity.ok("No artists found.");
            }

            return ResponseEntity.ok(artists);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
