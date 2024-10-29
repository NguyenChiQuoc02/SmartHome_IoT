package com.iot.websocket.Entity.dto;

public class LightRoomDTO {

  private boolean lightKitchen;
  private boolean lightBed;
  private boolean lightLivingRoom;



  public LightRoomDTO() {
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

  public LightRoomDTO(boolean lightKitchen, boolean lightBed, boolean lightLivingRoom) {
    this.lightKitchen = lightKitchen;
    this.lightBed = lightBed;
    this.lightLivingRoom = lightLivingRoom;
  }
  @Override
  public String toString() {
    return "LightRoomDTO{" +
            "lightKitchen=" + lightKitchen +
            ", lightBed=" + lightBed +
            ", lightLivingRoom=" + lightLivingRoom +
            '}';
  }
}
