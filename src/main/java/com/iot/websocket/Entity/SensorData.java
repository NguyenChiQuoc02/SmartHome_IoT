package com.iot.websocket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SensorData {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 1L;

    private double temperature;
    private double humidity;



    public SensorData() {}

    public SensorData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
