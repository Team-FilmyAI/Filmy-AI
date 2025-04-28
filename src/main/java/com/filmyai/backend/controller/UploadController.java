package com.filmyai.backend.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class UploadController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
            HttpServletRequest request,
            @RequestPart("json") String json,
            @RequestPart("file") MultipartFile file
    ) {
        System.out.println("Content-Type: " + request.getContentType());
        System.out.println("Received JSON: " + json);
        System.out.println("Received file: " + file.getOriginalFilename());
        return ResponseEntity.ok("Received");
    }
}
