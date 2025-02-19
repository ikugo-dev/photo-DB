package com.ikugo.photos.service;

import com.ikugo.photos.model.Photo;
import com.ikugo.photos.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service @Component
public class PhotoService {
    private final PhotoRepository database;

    @Autowired
    public PhotoService(PhotoRepository database) {
        this.database = database;
    }
    public PhotoRepository getData() {
        return database;
    }
    public Iterable<Photo> getPhotos() {
        return database.findAll();
    }
    public Photo getPhoto(UUID id) {
        return database.findById(id).orElse(null);
    }
    public void addPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        byte[] fileContent = file.getBytes();
        Photo photo = new Photo(fileName, fileType, fileContent);
        database.save(photo);
    }
    public void deletePhoto(UUID id) {
        database.deleteById(id);
    }
}
