package com.iot.websocket.controller;

import com.iot.websocket.Entity.Image;
import com.iot.websocket.Service.ImageService;
import com.iot.websocket.payload.FileUploadResponse;
import com.iot.websocket.utils.FileDownloadUtil;
import com.iot.websocket.utils.FileUploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")
public class ImageController {
  private final ImageService imageService;
  private boolean isRequestAllowed = true;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @Operation(summary = "Upload a fake image", description = "Uploads a fake image file.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Image.class))),
          @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
          @ApiResponse(responseCode = "404", description = "image not found", content = @Content),
          @ApiResponse(responseCode = "500", description = "Image error server", content = @Content)
  })
  @PostMapping("/fake")
  public ResponseEntity<FileUploadResponse> uploadFileFake(
          @Parameter(description = "File to upload", content = @Content(mediaType = "multipart/form-data"))
          @RequestParam("file") MultipartFile multipartFile,
          @RequestParam(value = "title", required = false, defaultValue = "fake")  String title )
          throws IOException {
    if (!isRequestAllowed) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    isRequestAllowed = false;


    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

    // Save file on disk
    String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);
    String fileUrl = fileCode + "-" + fileName;
    boolean isReal =false;
    this.imageService.addImage(fileUrl,title,isReal);

    FileUploadResponse response = new FileUploadResponse();
    response.setFileName(fileName);
    response.setTitle(title);
    response.setUrlImage(fileCode + "-" + fileName);

    // Đặt tác vụ để đặt lại cờ sau 5 giây
    scheduler.schedule(() -> {
      isRequestAllowed = true;
      System.out.println("Request is allowed again.");
    }, 5, TimeUnit.SECONDS);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @PostMapping("/real")
  public ResponseEntity<FileUploadResponse> uploadFileReal(
          @RequestParam("file") MultipartFile multipartFile,
          @RequestParam(value = "title", required = false, defaultValue = "unknown")  String title )
          throws IOException {
    if (!isRequestAllowed) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    isRequestAllowed = false;

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    long size = multipartFile.getSize();

    // Save file on disk
    String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);
    String fileUrl = fileCode + "-" + fileName;
    boolean isReal =true;
    this.imageService.addImage(fileUrl,title,isReal);

    FileUploadResponse response = new FileUploadResponse();
    response.setFileName(fileName);
    response.setTitle(title);
    response.setUrlImage(fileCode + "-" + fileName);
    // Đặt tác vụ để đặt lại cờ sau 5 giây
    scheduler.schedule(() -> {
      isRequestAllowed = true;
      System.out.println("Request is allowed again.");
    }, 5, TimeUnit.SECONDS);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<Image>> getAllImages(@RequestParam(value = "page" ,required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "limit",required = false, defaultValue = "5") int limit) {
    Page<Image> images = imageService.getAllImages(page, limit);
    return new ResponseEntity<>(images, HttpStatus.OK);
  }

  @GetMapping("/viewImage/{name}")
  public ResponseEntity<?> viewImage(@PathVariable("name") String name) {
    FileDownloadUtil downloadUtil = new FileDownloadUtil();
    Image image = this.imageService.getImageByUrl(name);
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



}
