package com.iot.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.websocket.Entity.SensorData;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

  private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // Handle incoming messages if needed
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
  }

  public void sendSensorData(SensorData sensorData) throws IOException {
    String json = objectMapper.writeValueAsString(sensorData);
    for (WebSocketSession session : sessions) {
      session.sendMessage(new TextMessage(json));
    }
  }
}
