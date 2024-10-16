package com.iot.websocket.controller;

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

@RestController
public class ImageController {

  @PostMapping("/uploadFile")
  public ResponseEntity<FileUploadResponse> uploadFile(
          @RequestParam("file") MultipartFile multipartFile)
          throws IOException {

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    long size = multipartFile.getSize();

    String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

    FileUploadResponse response = new FileUploadResponse();
    response.setFileName(fileName);
    response.setSize(size);
    response.setDownloadUri("/downloadFile/" + filecode);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/downloadFile/{fileCode}")
  public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
    FileDownloadUtil downloadUtil = new FileDownloadUtil();

    Resource resource = null;
    try {
      resource = downloadUtil.getFileAsResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }

    if (resource == null) {
      return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
    }

    String contentType = "application/octet-stream";
    String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
            .body(resource);
  }

  @GetMapping("/viewImage/{fileCode}")
  public ResponseEntity<?> viewImage(@PathVariable("fileCode") String fileCode) {
    FileDownloadUtil downloadUtil = new FileDownloadUtil();

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

}
