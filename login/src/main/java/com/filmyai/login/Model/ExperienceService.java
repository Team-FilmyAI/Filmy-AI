package com.filmyai.login.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    private static final String MOVIE_POSTER_DIR = "src/main/resources/static/images/movie_poster/";

    // Save the movie poster to the database
    public void saveExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    // Save movie poster to a directory and return its file path
    public String saveMoviePoster(MultipartFile moviePoster) throws IOException {
        if (moviePoster.isEmpty()) {
            throw new IOException("Movie poster is empty.");
        }

        // Create directory if it doesn't exist
        Path directoryPath = Paths.get(MOVIE_POSTER_DIR);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Save the file to the directory
        String fileName = System.currentTimeMillis() + "_" + moviePoster.getOriginalFilename();
        Path filePath = directoryPath.resolve(fileName);
        Files.write(filePath, moviePoster.getBytes());

        return "/images/movie_poster/" + fileName;
    }

    
}
