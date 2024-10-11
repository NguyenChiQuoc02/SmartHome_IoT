package com.iot.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class DemoAppWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAppWebsocketApplication.class, args);
	}

}
