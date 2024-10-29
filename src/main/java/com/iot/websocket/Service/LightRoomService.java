package com.iot.websocket.Service;

import com.iot.websocket.Entity.LightRoom;
import com.iot.websocket.Entity.SensorData;
import com.iot.websocket.Repository.LightRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LightRoomService {

  private final LightRoomRepository lightRoomRepository;

  public LightRoomService(LightRoomRepository lightRoomRepository) {
    this.lightRoomRepository = lightRoomRepository;
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
