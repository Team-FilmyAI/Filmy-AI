package com.filmyai.backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.filmyai.backend.dto.ArtistCreationDto;

import com.filmyai.backend.model.Artist;

import com.filmyai.backend.repository.ArtistRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	private static final String PROFILE_PHOTOS_DIR = "src/main/resources/static/images/profile_pictures/artist/";

	public Artist createArtistProfile(ArtistCreationDto artistCreationRequest, MultipartFile profilePhoto)
			throws IOException {

		if (artistRepository.findByEmail(artistCreationRequest.getEmail()).isPresent()) {
			throw new RuntimeException("Artist profile  already exists!");
		}

		Artist artist = new Artist();

		String profilePhotoPath = saveProfilePhoto(profilePhoto);

		artist.setFirstName(artistCreationRequest.getFirstName());
		artist.setLastName(artistCreationRequest.getLastName());
		artist.setEmail(artistCreationRequest.getEmail());
		artist.setContactNumber(artistCreationRequest.getContactNumber());
		artist.setOccupation(artistCreationRequest.getOccupation());
		artist.setLocation(artistCreationRequest.getLocation());
		artist.setProfilePhotoPath(profilePhotoPath);
		artist.setPortfolio(artistCreationRequest.getPortfolio());

		return artistRepository.save(artist);
	}

	// Save profile picture to a directory and return its file path
	public String saveProfilePhoto(MultipartFile profilePhoto) throws IOException {

		if (profilePhoto.isEmpty()) {
			throw new IOException("Profile picture is empty.");
		}

		// Create directory if it doesn't exist
		Path directoryPath = Paths.get(PROFILE_PHOTOS_DIR);
		if (!Files.exists(directoryPath)) {
			Files.createDirectories(directoryPath);
		}

		// Save the file to the directory
		String fileName = System.currentTimeMillis() + "_" + profilePhoto.getOriginalFilename();
		Path filePath = directoryPath.resolve(fileName);
		Files.write(filePath, profilePhoto.getBytes());

		return "/images/profile_pictures/artist/" + fileName;
	}

}
