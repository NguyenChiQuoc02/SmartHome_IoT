package com.iot.websocket.Repository;

import com.iot.websocket.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
  Image findByUrlImage(String urlImage);
}
