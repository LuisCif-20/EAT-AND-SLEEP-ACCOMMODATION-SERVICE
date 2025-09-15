package com.sa.accommodation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AccommodationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccommodationServiceApplication.class, args);
	}

}
