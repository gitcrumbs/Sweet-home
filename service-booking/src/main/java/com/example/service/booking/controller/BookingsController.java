package com.example.service.booking.controller;

import com.example.service.booking.entities.Booking;
import com.example.service.booking.entities.Transaction;
import com.example.service.booking.service.BookingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class BookingsController {

    BookingsService bookingsService;
    public  BookingsController(BookingsService bookingsService){
        this.bookingsService=bookingsService;
    }

    @GetMapping("/status")
    public ResponseEntity getOrder(){

        return ResponseEntity.ok(bookingsService.getAllBookings());

    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking trxVo){

        return new ResponseEntity<>(bookingsService.createBooking(trxVo),HttpStatus.CREATED);

    }

    @PostMapping("/booking/{id}/transaction")
    public ResponseEntity getBookingStatus(@RequestBody Transaction trxVo, @PathVariable Integer id){
        System.out.println("Inside Booking Transaction status "+trxVo);
        return ResponseEntity.ok(bookingsService.createTrx(trxVo,id));

    }

    @GetMapping("/booking/{id}")
    public ResponseEntity createBooking(@PathVariable Integer id){
        Optional<Booking> response = bookingsService.getBookingStatus(id);
        if(response.isPresent()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.of( response);
        }


    }

}
