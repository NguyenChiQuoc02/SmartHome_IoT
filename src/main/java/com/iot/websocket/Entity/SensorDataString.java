package com.iot.websocket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SensorDataString {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String event="sensor";

  private String temperature;
  private String humidity;
  private double gas;

  public SensorDataString() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public String getHumidity() {
    return humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  public double getGas() {
    return gas;
  }

  public void setGas(double gas) {
    this.gas = gas;
  }

  public SensorDataString(Long id, String event, String temperature, String humidity, double gas) {
    this.id = id;
    this.event = event;
    this.temperature = temperature;
    this.humidity = humidity;
    this.gas = gas;
  }
}
