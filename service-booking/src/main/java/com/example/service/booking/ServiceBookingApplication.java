package com.example.service.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class ServiceBookingApplication {


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ServiceBookingApplication.class, args);
	}

}
