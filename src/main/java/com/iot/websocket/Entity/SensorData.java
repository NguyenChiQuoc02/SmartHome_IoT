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
    private double gas;
    private double light;

    public SensorData() {
    }

    public SensorData(Long id, double temperature, double humidity, double gas, double light) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.gas = gas;
        this.light = light;
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

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }
}
