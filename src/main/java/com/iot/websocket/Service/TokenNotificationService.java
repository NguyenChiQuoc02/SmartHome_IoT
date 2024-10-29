package com.iot.websocket.Service;

import com.iot.websocket.Entity.TokenNotification;
import com.iot.websocket.Repository.TokenNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenNotificationService {

  private final TokenNotificationRepository tokenNotificationRepository;

  public TokenNotificationService(TokenNotificationRepository tokenNotificationRepository) {
    this.tokenNotificationRepository = tokenNotificationRepository;
  }

  public TokenNotification saveToken(TokenNotification token) {
    Optional<TokenNotification> existingToken = tokenNotificationRepository.findByToken(token.getToken());

    if (existingToken.isPresent()) {
      return existingToken.get();
    } else {
      return tokenNotificationRepository.save(token);
    }
  }
}
