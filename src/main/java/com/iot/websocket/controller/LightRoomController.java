package com.iot.websocket.controller;

import com.iot.websocket.Entity.LightRoom;
import com.iot.websocket.Entity.SensorData;
import com.iot.websocket.Entity.dto.LightRoomDTO;
import com.iot.websocket.Service.LightRoomService;
import com.iot.websocket.config.Appconfig;
import org.springframework.http.HttpStatus;
import com.iot.websocket.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import javax.swing.text.TabExpander;

@CrossOrigin(origins = "*")
@RestController
@EnableScheduling
public class LightRoomController {

  @Autowired
  private RestTemplate restTemplate;

  private  final LightRoomService lightRoomService;
  private final WebSocketHandler webSocketHandler;

  public LightRoomController(LightRoomService lightRoomService,WebSocketHandler webSocketHandler) {
    this.lightRoomService = lightRoomService;
    this.webSocketHandler = webSocketHandler;
  }

  @PostMapping("/light")
  public LightRoom sendLightData(@RequestBody LightRoom lightRoom) throws IOException {
    LightRoom savelightRoom =  this.lightRoomService.saveLightData(lightRoom);
    webSocketHandler.sendLightData(savelightRoom);
    return  savelightRoom;
  }
//
//  public void sendLightControlData() {
//    String url = "http://10.10.26.91";
//
//    // Tạo object chứa data cần gửi
//    LightControlRequest requestData = new LightControlRequest();
//    requestData.setLightKitchen(true);
//    requestData.setLightBed(false);
//    requestData.setLightLivingRoom(false);
//    requestData.setIsServo(true);
//
//    // Tạo headers
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//
//    // Tạo request
//    HttpEntity<LightControlRequest> requestEntity = new HttpEntity<>(requestData, headers);
//
//    // Gửi request POST
//    restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
//  }


//  @PostMapping("/lights")
//  public ResponseEntity<LightRoomDTO> sendLightsData(@RequestBody LightRoomDTO lightRoomDTO) {
//    String url = "http://10.10.25.106:80";
//    LightRoomDTO data = new LightRoomDTO();
//
//    data.setLightKitchen(lightRoomDTO.isLightKitchen());
//    data.setLightBed(lightRoomDTO.isLightBed());
//    data.setLightLivingRoom(lightRoomDTO.isLightLivingRoom());
//
//    restTemplate.postForObject(url, data, String.class);
////    System.out.println(lightRoomDTO.isLightLivingRoom());
//    return ResponseEntity.ok(data);
//  }

//  @Scheduled(fixedRate = 1000) // 1 second interval
//  public void sendLightsData() {
//    String url = "http://10.10.25.106:80";
//
//    LightRoomDTO data = new LightRoomDTO();
//    data.setLightKitchen(true);
//    data.setLightBed(true);
//    data.setLightLivingRoom(true);
//
//    // Send the POST request
//    try {
//      String response = restTemplate.postForObject(url, data, String.class);
//      System.out.println("Response: " + response); // Print the response for debugging
//    } catch (Exception e) {
//      System.err.println("Error sending data: " + e.getMessage());
//    }
//  }
//
//  @GetMapping("/light")
//  public LightRoom getlight(){
//    LightRoom newLight = this.lightRoomService.getLightRoom();
//    return newLight;
//  }

  @GetMapping()
  public String getString(){
    return "Hello";
  }


}
