package com.iot.websocket.Service;

import com.iot.websocket.Entity.Image;
import com.iot.websocket.Repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

  private final ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image getImageByUrl(String urlImage) {
    return imageRepository.findByUrlImage(urlImage);
  }

  public Image addImage(String file,String title, boolean isReal){

    if(isReal){
      Image image = new Image();
      image.setTitle("real_" + title);
      image.setUrlImage(file);
      image.setReceivedTime(LocalDateTime.now());
      return imageRepository.save(image);
    }else{
      Image image = new Image();
      image.setTitle("fake");
      image.setUrlImage(file);
      image.setReceivedTime(LocalDateTime.now());
      return imageRepository.save(image);
    }

  }

  public Page<Image> getAllImages(int page, int limit) {
    return imageRepository.findAll(PageRequest.of(page - 1, limit, Sort.by("receivedTime").descending()));
  }

}
