package com.filmy_ai.musician.musician_profile.controller;

import com.filmy_ai.musician.musician_profile.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/musician")
public class ImageUploadController {

    @Autowired
    private ImageStorageService imageStorageService;

    // Profile Picture create
    @PostMapping("/upload/profile-picture/{profileId}")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long profileId,
            @RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(imageStorageService.uploadProfilePicture(profileId, file));
    }

    @PostMapping("upload/experience-poster/{experienceId}")
    public ResponseEntity<String> uploadExperiencePoster(
            @PathVariable Long experienceId,
            @RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(imageStorageService.uploadExperiencePoster(experienceId, file));
    }

    // Delete profile picture
    @DeleteMapping("/profile-picture/{profileId}")
    public ResponseEntity<String> deleteProfilePicture(@PathVariable Long profileId) throws IOException {
        return ResponseEntity.ok(imageStorageService.deleteProfilePicture(profileId));
    }

    @DeleteMapping("/experience-poster/{experienceId}")
    public ResponseEntity<String> deleteExperiencePoster(@PathVariable Long experienceId) throws IOException {
        return ResponseEntity.ok(imageStorageService.deleteExperiencePoster(experienceId));
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        return ResponseEntity.ok(imageStorageService.loadFile(filename));
    }

}
