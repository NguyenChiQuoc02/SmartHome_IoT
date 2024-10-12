package com.iot.websocket.controller;

import com.iot.websocket.Entity.LightRoom;
import com.iot.websocket.Entity.SensorData;
import com.iot.websocket.Service.LightRoomService;
import com.iot.websocket.handler.WebSocketHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableScheduling
public class LightRoomController {

  private  final LightRoomService lightRoomService;
  private final WebSocketHandler webSocketHandler;

  public LightRoomController(LightRoomService lightRoomService,WebSocketHandler webSocketHandler) {
    this.lightRoomService = lightRoomService;
    this.webSocketHandler = webSocketHandler;
  }

  @PostMapping("/light")
  public LightRoom sendLightData(@RequestBody LightRoom lightRoom) throws IOException {
    LightRoom savelightRoom =  this.lightRoomService.saveLightData(lightRoom);
//    webSocketHandler.sendSensorData(savelightRoom);
    return  savelightRoom;
  }

  @GetMapping("/light")
  public LightRoom getlight(){
    LightRoom newLight = this.lightRoomService.getLightRoom();
    return newLight;
  }

  @GetMapping()
  public String getString(){
    return "Hello";
  }

}
