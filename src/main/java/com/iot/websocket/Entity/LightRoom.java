package com.iot.websocket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LightRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String event = "light";
   private boolean lightKitchen;
   private boolean lightBed;
   private boolean lightLivingRoom;

  public LightRoom() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isLightKitchen() {
    return lightKitchen;
  }

  public void setLightKitchen(boolean lightKitchen) {
    this.lightKitchen = lightKitchen;
  }

  public boolean isLightBed() {
    return lightBed;
  }

  public void setLightBed(boolean lightBed) {
    this.lightBed = lightBed;
  }

  public boolean isLightLivingRoom() {
    return lightLivingRoom;
  }

  public void setLightLivingRoom(boolean lightLivingRoom) {
    this.lightLivingRoom = lightLivingRoom;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public LightRoom(Long id, String event, boolean lightKitchen, boolean lightBed, boolean lightLivingRoom) {
    this.id = id;
    this.lightKitchen = lightKitchen;
    this.event = event;
    this.lightBed = lightBed;
    this.lightLivingRoom = lightLivingRoom;
  }
}
