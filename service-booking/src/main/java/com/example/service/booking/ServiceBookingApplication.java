package com.example.service.booking;

import com.example.service.booking.Dao.BookingsDao;
import com.example.service.booking.entities.Booking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

@RestController
@SpringBootApplication
public class ServiceBookingApplication {
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ServiceBookingApplication.class, args);

		BookingsDao bookingsDao = context.getBean(BookingsDao.class);

		//Dummy Data for Database Check validations
		Booking booking = new Booking();
		booking.setBookingId(1);

		booking.setBookedOn(LocalDateTime.now());
		booking.setAadharNumber("454654874674");
		booking.setRoomNumbers("502");
		booking.setFromDate(LocalDateTime.now().toString());
		booking.setToDate(LocalDateTime.now().toString());

		booking.setTransactionId(1234);
		booking.setRoomPrice(2000);

		System.out.println("Before Saving"+booking);

		Booking savedCustomer = bookingsDao.save(booking);

		Booking retrivedCustomer = bookingsDao.findbyId(savedCustomer.getBookingId());
		System.out.println("Retrieved Booking"+retrivedCustomer);


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();

	}

}
