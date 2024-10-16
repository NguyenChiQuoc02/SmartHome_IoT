package com.iot.websocket.payload;


public class FileUploadResponse {
  private String fileName;
  private String downloadUri;
  private long size;

  public FileUploadResponse() {
  }

  public FileUploadResponse(String fileName, String downloadUri, long size) {
    this.fileName = fileName;
    this.downloadUri = downloadUri;
    this.size = size;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getDownloadUri() {
    return downloadUri;
  }

  public void setDownloadUri(String downloadUri) {
    this.downloadUri = downloadUri;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }
}
