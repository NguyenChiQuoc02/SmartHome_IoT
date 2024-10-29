package com.iot.websocket.Service;

import com.iot.websocket.Entity.Image;
import com.iot.websocket.Repository.ImageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

  private final ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }

  public Image getImageById(long id){
    Optional<Image>  image = this.imageRepository.findById(id);
    if (image.isPresent()) {
      return image.get();
    }
    return null;
  }
  public Image addImage(String file){

    // Save URL in database
    Image image = new Image();
    image.setUrlImage(file);
    return imageRepository.save(image);
  }

}
