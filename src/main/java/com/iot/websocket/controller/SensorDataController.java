package com.iot.websocket.controller;

import com.iot.websocket.Entity.SensorData;
import com.iot.websocket.Service.SensorDataService;
import com.iot.websocket.handler.WebSocketHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableScheduling
public class SensorDataController {

    private final SensorDataService sensorDataService;
    private final WebSocketHandler webSocketHandler;

    public SensorDataController(WebSocketHandler webSocketHandler,SensorDataService sensorDataService) {
        this.webSocketHandler = webSocketHandler;
        this.sensorDataService = sensorDataService;
    }


//    @Scheduled(fixedRate = 500)
    @PostMapping()
    public SensorData sendSensorData(@RequestBody SensorData sensorData) throws IOException {
        SensorData saveSensorData =  this.sensorDataService.saveSensorData(sensorData);
        webSocketHandler.sendSensorData(sensorData);
        return  saveSensorData;
    }


}
