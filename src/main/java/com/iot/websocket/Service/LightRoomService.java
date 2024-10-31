package com.iot.websocket.Service;

import com.iot.websocket.Entity.LightRoom;
import com.iot.websocket.Repository.LightRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LightRoomService {

  private final LightRoomRepository lightRoomRepository;
  private final RestTemplate restTemplate;

  public LightRoomService(LightRoomRepository lightRoomRepository, RestTemplate restTemplate) {

    this.lightRoomRepository = lightRoomRepository;
    this.restTemplate= restTemplate;
  }

  public LightRoom getLightRoomById() {
    Optional<LightRoom> lightRoom = this.lightRoomRepository.findById(1L);
    if (lightRoom.isPresent()) {
      return lightRoom.get();
    }
    return null;
  }

  public LightRoom saveLightData(LightRoom lightRoom){
//    LightRoom lightRoom1 = this.getLightRoomById();
    return this.lightRoomRepository.save(lightRoom);
  }

}
