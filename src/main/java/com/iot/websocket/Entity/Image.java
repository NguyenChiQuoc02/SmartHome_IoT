package com.iot.websocket.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String urlImage;
  private String title;
  @Column(name = "received_time")
  private LocalDateTime receivedTime;

  public Image() {
  }

  public Image(Long id, String urlImage, String title, LocalDateTime receivedTime) {
    this.id = id;
    this.urlImage = urlImage;
    this.title = title;
    this.receivedTime = receivedTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(LocalDateTime receivedTime) {
    this.receivedTime = receivedTime;
  }
}
