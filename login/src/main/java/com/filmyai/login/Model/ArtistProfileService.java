package com.filmyai.login.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ArtistProfileService {

    @Autowired
    private ArtistProfileRepository artistProfileRepository;

    private static final String PROFILE_PICTURES_DIR = "src/main/resources/static/profile_pictures/artist/";

    // Save the artist profile to the database
    public void saveArtistProfile(ArtistProfile artistProfile) {
        artistProfileRepository.save(artistProfile);
    }

    // Save profile picture to a directory and return its file path
    public String saveProfilePicture(MultipartFile profilePicture) throws IOException {
        if (profilePicture.isEmpty()) {
            throw new IOException("Profile picture is empty.");
        }

        // Create directory if it doesn't exist
        Path directoryPath = Paths.get(PROFILE_PICTURES_DIR);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Save the file to the directory
        String fileName = System.currentTimeMillis() + "_" + profilePicture.getOriginalFilename();
        Path filePath = directoryPath.resolve(fileName);
        Files.write(filePath, profilePicture.getBytes());

        return "/profile_pictures/artist/" + fileName;
    }


}
