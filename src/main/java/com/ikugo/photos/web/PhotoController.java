package com.ikugo.photos.web;

import com.ikugo.photos.model.Photo;
import com.ikugo.photos.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@RestController
public class PhotoController {
    private final PhotoService photoService;
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhotoDB() {
        return photoService.getPhotos();
    }
    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable UUID id) {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Photo with id " + id + " not found.");
        }
        return photo;
    }

    @PostMapping("/photos")
    public void addPhoto(@RequestPart("payload") MultipartFile file) throws IOException {
        photoService.addPhoto(file);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable UUID id) {
        //doesnt throw ResponseStatusException
        photoService.deletePhoto(id);
    }
}
