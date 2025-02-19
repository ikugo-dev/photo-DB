package com.ikugo.photos;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotoController {
    private Map<UUID, Photo> photoDB = new HashMap<>();

    {
        photoDB.put(UUID.randomUUID(), new Photo(UUID.randomUUID(), "photo1.jpg", new byte[0]));
        photoDB.put(UUID.randomUUID(), new Photo(UUID.randomUUID(), "photo2.jpg", new byte[0]));
        photoDB.put(UUID.randomUUID(), new Photo(UUID.randomUUID(), "photo3.jpg", new byte[0]));
        photoDB.put(UUID.randomUUID(), new Photo(UUID.randomUUID(), "photo4.jpg", new byte[0]));
    }

    @GetMapping("/photos")
    public Map<UUID, Photo> getPhotoDB() {
        return photoDB;
    }
    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable UUID id) {
        Photo photo = photoDB.get(id);
        if (photo == null) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Photo with id " + id + " not found.");
        }
        return photo;
    }

    @PostMapping("/photos/")
    public void addPhoto(@RequestPart("payload") MultipartFile file) throws IOException {
        UUID photoID = UUID.randomUUID();
        String fileName = file.getOriginalFilename();
        byte[] fileContent = file.getBytes();
        Photo photo = new Photo(photoID, fileName, fileContent);
        photoDB.put(photoID, photo);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable UUID id) {
        Photo photo = photoDB.remove(id);
        if (photo == null) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Photo with id " + id + " not found.");
        }
    }
}
