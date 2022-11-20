package com.example.service.booking.controller;


import com.example.service.booking.entities.Booking;
import com.example.service.booking.service.BookingProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bookings")
public class BookingsController {

    BookingProcessingService bookingProcessingService ;

    public  BookingsController(BookingProcessingService service){
        this.bookingProcessingService = service;
    }

    @GetMapping("/serviceOrders")
    public String getOrder(){
        System.out.println("Method Get ServiceOrders");
        return "Hello Students  from @RestController demo";

    }

    @PostMapping("/serviceOrders")
    public ResponseEntity createOrder(@RequestBody Booking bookingVO){
        System.out.println("Received from client"+bookingVO);
        return ResponseEntity.ok(bookingProcessingService.createBooking(bookingVO));

    }
}
