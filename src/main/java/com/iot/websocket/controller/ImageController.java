package com.iot.websocket.controller;

import com.iot.websocket.Entity.Image;
import com.iot.websocket.Service.ImageService;
import com.iot.websocket.payload.FileUploadResponse;
import com.iot.websocket.utils.FileDownloadUtil;
import com.iot.websocket.utils.FileUploadUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ImageController {
  private final ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping("/uploadFile")
  public ResponseEntity<FileUploadResponse> uploadFile(
          @RequestParam("file") MultipartFile multipartFile)
          throws IOException {

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    long size = multipartFile.getSize();

    // Save file on disk
    String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);
    String fileUrl = fileCode + "-" + fileName;

   this.imageService.addImage(fileUrl);

    FileUploadResponse response = new FileUploadResponse();
    response.setFileName(fileName);
    response.setSize(size);
    response.setDownloadUri("/downloadFile/" + fileCode);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/viewImage/{id}")
  public ResponseEntity<?> viewImage(@PathVariable("id") long id) {
    FileDownloadUtil downloadUtil = new FileDownloadUtil();
    Image image = this.imageService.getImageById(id);
    String fileCode = image.getUrlImage();
    Resource resource = null;
    try {
      resource = downloadUtil.getFileAsResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    if (resource == null) {
      return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
    }

    // Lấy kiểu MIME từ đuôi file để hiển thị đúng định dạng ảnh
    String contentType = null;
    try (InputStream inputStream = resource.getInputStream()) {
      contentType = URLConnection.guessContentTypeFromStream(inputStream);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }

    if (contentType == null) {
      contentType = "application/octet-stream"; // Default nếu không xác định được
    }

    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .body(resource);
  }

  @GetMapping("/images")
  public ResponseEntity<List<Image>> listImages() {
    List<Image> images = imageService.getAllImages();
    return ResponseEntity.ok(images);
  }

}
