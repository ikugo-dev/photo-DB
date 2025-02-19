package com.ikugo.photos.repository;

import com.ikugo.photos.model.Photo;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface PhotoRepository extends CrudRepository<Photo, UUID> {
}
