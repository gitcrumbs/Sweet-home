package com.example.service.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication

public class ServiceBookingApplication {
	public static void main(String[] args) {

		SpringApplication.run(ServiceBookingApplication.class, args);

	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();

	}
}