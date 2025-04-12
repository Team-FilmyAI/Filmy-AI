package com.filmy_ai.non_artist.non_artist.service;


import com.filmy_ai.non_artist.non_artist.exception.ResourceNotFoundException;
import com.filmy_ai.non_artist.non_artist.model.Experience;
import com.filmy_ai.non_artist.non_artist.model.NonArtistProfile;
import com.filmy_ai.non_artist.non_artist.repository.ExperienceRepository;
import com.filmy_ai.non_artist.non_artist.repository.NonArtistProfileRepo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {

    private final NonArtistProfileRepo nonArtistProfileRepo;
    private final ExperienceRepository experienceRepository;

    public ImageStorageService(NonArtistProfileRepo nonArtistProfileRepo, ExperienceRepository experienceRepository) {
        this.nonArtistProfileRepo = nonArtistProfileRepo;
        this.experienceRepository = experienceRepository;
    }

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(String filename, MultipartFile file) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return "/api/upload/file/" + filename;
    }


    public void deleteFile(String fileUrl) throws IOException {
        if (fileUrl != null && fileUrl.contains("/api/upload/file/")) {
            String filename = fileUrl.replace("/api/upload/file/", "");
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.deleteIfExists(path);
        }
    }


    public Resource loadFile(String filename) throws MalformedURLException {
        Path path = Paths.get(UPLOAD_DIR).resolve(filename);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists()) {
            throw new MalformedURLException("File not found");
        }
        return resource;
    }

    // non artist profile pic
    public String uploadProfilePicture(Long profileId, MultipartFile file) throws IOException {
        String filename = "profile_" + profileId + "_" + file.getOriginalFilename();
        String fileUrl = saveFile(filename, file);

        NonArtistProfile profile = nonArtistProfileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profile.setProfilePicturePath(fileUrl);
        nonArtistProfileRepo.save(profile);

        return "Profile picture uploaded successfully";
    }


    // Experience poster
    public String uploadExperiencePoster(Long experienceId, MultipartFile file) throws IOException {
        String filename = "poster_" + experienceId + "_" + file.getOriginalFilename();
        String fileUrl = saveFile(filename, file);

        Experience exp = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));
        exp.setPosterUrl(fileUrl);
        experienceRepository.save(exp);

        return "Poster uploaded successfully";
    }


    public String deleteProfilePicture(Long profileId) throws IOException {
        NonArtistProfile profile = nonArtistProfileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        String path = profile.getProfilePicturePath();
        deleteFile(path);
        profile.setProfilePicturePath(null);
        nonArtistProfileRepo.save(profile);

        return "Profile picture deleted";
    }


    public String deleteExperiencePoster(Long experienceId) throws IOException {
        Experience exp = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        String path = exp.getPosterUrl();
        deleteFile(path);
        exp.setPosterUrl(null);
        experienceRepository.save(exp);

        return "Experience poster deleted";
    }






}
