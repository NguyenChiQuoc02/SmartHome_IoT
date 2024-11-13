package com.iot.websocket.Service;

import com.iot.websocket.Entity.SensorData;
import com.iot.websocket.Repository.SensorDataRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

import org.slf4j.Logger;


@Service
public class SensorDataService {

  private final SensorDataRepository sensorDataRepository;

  public SensorDataService(SensorDataRepository sensorDataRepository) {
    this.sensorDataRepository = sensorDataRepository;
  }


  public SensorData saveSensorData(SensorData sensorData){
    return this.sensorDataRepository.save(sensorData);
  }

}
