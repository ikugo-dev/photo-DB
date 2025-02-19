package com.ikugo.photos;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service @Component
public class PhotoService {
    private Map<UUID, Photo> database = new HashMap<>();
    {
        database.put(UUID.randomUUID(), new Photo("photo1.jpg", "image/jpeg", new byte[0]));
        database.put(UUID.randomUUID(), new Photo("photo2.jpg", "image/jpeg", new byte[0]));
        database.put(UUID.randomUUID(), new Photo("photo3.jpg", "image/jpeg", new byte[0]));
        database.put(UUID.randomUUID(), new Photo("photo4.jpg", "image/jpeg", new byte[0]));
    }

    public Map<UUID, Photo> getData() {
        return database;
    }
    public Photo[] getPhotos() {
        return database.values().toArray(new Photo[0]);
    }
    public Photo getPhoto(UUID id) {
        return database.get(id);
    }
    public void addPhoto(Photo photo) {
        database.put(UUID.randomUUID(), photo);
    }
    public Photo deletePhoto(UUID id) {
        return database.remove(id);
    }
}
