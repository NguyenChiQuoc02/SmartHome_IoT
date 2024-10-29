package com.iot.websocket.controller;

import com.iot.websocket.Entity.TokenNotification;
import com.iot.websocket.Service.TokenNotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

  private final TokenNotificationService tokenNotificationService;

  public TokenController(TokenNotificationService tokenNotificationService) {
    this.tokenNotificationService = tokenNotificationService;
  }

  @PostMapping("/token")
  public TokenNotification createToken(@RequestBody TokenNotification token){
    return this.tokenNotificationService.saveToken(token);
  }

}
