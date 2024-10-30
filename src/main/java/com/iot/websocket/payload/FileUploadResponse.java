package com.iot.websocket.payload;


public class FileUploadResponse {
  private String fileName;
  private String UrlImage;
  private String title;

  public FileUploadResponse() {
  }

  public FileUploadResponse(String fileName, String urlImage, String title) {
    this.fileName = fileName;
    UrlImage = urlImage;
    this.title = title;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getUrlImage() {
    return UrlImage;
  }

  public void setUrlImage(String urlImage) {
    UrlImage = urlImage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
