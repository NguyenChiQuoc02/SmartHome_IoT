package com.iot.websocket.Service;

import com.iot.websocket.Entity.SensorDataString;
import com.iot.websocket.Repository.SensorDataStringRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorStringService {

  private final SensorDataStringRepository sensorDataStringRepository;

  public SensorStringService(SensorDataStringRepository sensorDataStringRepository) {
    this.sensorDataStringRepository = sensorDataStringRepository;
  }


  public SensorDataString saveSensorString(SensorDataString sensorDataString){
    return this.sensorDataStringRepository.save(sensorDataString);
  }
}
