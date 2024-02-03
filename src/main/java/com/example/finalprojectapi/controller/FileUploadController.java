package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.service.file.FileUploadService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/bo/v1")
@RequiredArgsConstructor
public class FileUploadController extends RestControllerConfig {

    private final FileUploadService fileUploadService;

    @PostMapping("/files/upload-image")
    public ResponseEntity<?> uploadImage(@Valid @NotNull @RequestPart(name= "file_data") MultipartFile fileData) throws Exception{
        return ok(fileUploadService.uploadImage(fileData));
    }
}
