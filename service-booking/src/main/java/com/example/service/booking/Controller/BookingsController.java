package com.example.service.booking.Controller;


import com.example.service.booking.entities.Booking;
import com.example.service.booking.entities.Transaction;
import com.example.service.booking.exceptions.RecordNotFoundException;
import com.example.service.booking.service.BookingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/hotel")
public class BookingsController {

    BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping("/status")
    public ResponseEntity getOrder() {

        return ResponseEntity.ok(bookingsService.getAllBookings());

    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking trxVo) {

        return new ResponseEntity<>(bookingsService.createBooking(trxVo), HttpStatus.CREATED);

    }

    @PostMapping("/booking/{id}/transaction")
    public ResponseEntity getBookingStatus(@Valid @RequestBody Transaction trxVo, @PathVariable Integer id) {
        System.out.println("Inside Booking Transaction status " + trxVo);

        Booking bookingentry = bookingsService.getBookingStatus(id).orElseThrow(() -> new RecordNotFoundException("Booking not found for the given id "+id));
        Booking bookingentryservice = new  Booking();
        if(bookingentry.getBookingId()!=null){
            System.out.println("Booking id found "+bookingentry.getBookingId() );
            bookingentryservice =  bookingsService.createTrx(trxVo, id,bookingentry);
        }
        return ResponseEntity.ok(bookingentryservice);

    }

    @GetMapping("/booking/{id}")
    public Booking createBooking(@PathVariable Integer id) {
        Optional<Booking> response = bookingsService.getBookingStatus(id);

        return  response.orElseThrow(()-> new RecordNotFoundException("Booking not generated for the id :"+id));


    }
}