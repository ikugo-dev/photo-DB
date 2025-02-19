package com.ikugo.photos.web;

import com.ikugo.photos.model.Photo;
import com.ikugo.photos.service.PhotoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class DownloadController {
    private final PhotoService photoService;
    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Photo with id " + id + " not found.");
        }
        byte[] fileContent = photo.getFileContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getFileType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
