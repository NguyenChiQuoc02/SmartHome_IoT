package com.iot.websocket.Repository;

import com.iot.websocket.Entity.TokenNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenNotificationRepository extends JpaRepository<TokenNotification,Long> {

  Optional<TokenNotification> findByToken(String token);
}
