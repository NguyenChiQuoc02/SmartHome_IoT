package com.iot.websocket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LightRoom {
  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = 1L;
   private double lightRoom1;
   private double lightRoom2;
   private double lightLivingRoom;

  public LightRoom() {
  }

  public LightRoom(Long id, double lightRoom1, double lightRoom2, double lightLivingRoom) {
    this.id = id;
    this.lightRoom1 = lightRoom1;
    this.lightRoom2 = lightRoom2;
    this.lightLivingRoom = lightLivingRoom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getLightRoom1() {
    return lightRoom1;
  }

  public void setLightRoom1(double lightRoom1) {
    this.lightRoom1 = lightRoom1;
  }

  public double getLightRoom2() {
    return lightRoom2;
  }

  public void setLightRoom2(double lightRoom2) {
    this.lightRoom2 = lightRoom2;
  }

  public double getLightLivingRoom() {
    return lightLivingRoom;
  }

  public void setLightLivingRoom(double lightLivingRoom) {
    this.lightLivingRoom = lightLivingRoom;
  }
}
