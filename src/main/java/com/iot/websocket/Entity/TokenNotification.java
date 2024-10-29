package com.iot.websocket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TokenNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String token;

  public TokenNotification() {
  }

  public TokenNotification(long id, String token) {
    this.id = id;
    this.token = token;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
