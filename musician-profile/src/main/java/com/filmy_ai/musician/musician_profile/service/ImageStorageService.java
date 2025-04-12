package com.filmy_ai.musician.musician_profile.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.Experience;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.repository.ExperienceRepository;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImageStorageService {

    private final MusicianProfileRepository profileRepo;
    private final ExperienceRepository experienceRepo;

    public ImageStorageService(MusicianProfileRepository profileRepo, ExperienceRepository experienceRepo) {
        this.profileRepo = profileRepo;
        this.experienceRepo = experienceRepo;
    }



    private static final String UPLOAD_DIR = "uploads/";

    public String uploadProfilePicture(Long profileId, MultipartFile file) throws IOException {
        String filename = "profile_" + profileId + "_" + file.getOriginalFilename();
        String fileUrl = saveFile(filename, file);

        MusicianProfile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profile.setProfilePicturePath(fileUrl);
        profileRepo.save(profile);

        return "Profile picture uploaded successfully";
    }

    public String uploadExperiencePoster(Long experienceId, MultipartFile file) throws IOException {
        String filename = "poster_" + experienceId + "_" + file.getOriginalFilename();
        String fileUrl = saveFile(filename, file);

        Experience exp = experienceRepo.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));
        exp.setPosterUrl(fileUrl);
        experienceRepo.save(exp);

        return "Poster uploaded successfully";
    }

    public String deleteProfilePicture(Long profileId) throws IOException {
        MusicianProfile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        String path = profile.getProfilePicturePath();
        deleteFile(path);
        profile.setProfilePicturePath(null);
        profileRepo.save(profile);

        return "Profile picture deleted";
    }

    public String deleteExperiencePoster(Long experienceId) throws IOException {
        Experience exp = experienceRepo.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        String path = exp.getPosterUrl();
        deleteFile(path);
        exp.setPosterUrl(null);
        experienceRepo.save(exp);

        return "Experience poster deleted";
    }



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

}