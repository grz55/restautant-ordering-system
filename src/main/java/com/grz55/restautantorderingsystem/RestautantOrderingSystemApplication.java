package com.grz55.restautantorderingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RestautantOrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestautantOrderingSystemApplication.class, args);
	}
}
