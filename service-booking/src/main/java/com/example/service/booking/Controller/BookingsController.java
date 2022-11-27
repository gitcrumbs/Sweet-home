package com.example.service.booking.Controller;


import com.example.service.booking.entities.Booking;
import com.example.service.booking.entities.Transaction;
import com.example.service.booking.exceptions.BookingNotFoundException;
import com.example.service.booking.exceptions.RecordNotFoundException;
import com.example.service.booking.service.BookingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(BookingsController.class);
    BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping("/status")
    public ResponseEntity getOrder() {

        return ResponseEntity.ok(bookingsService.getAllBookings());

    }

    /* The post mapping call with the booking endpoint passes the booking object as payload
     to the createBooking method defined in the BookingService layer and it in returns the
      generates the booking entry in the database .
     * */
    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking trxVo) throws Exception {

        return new ResponseEntity<>(bookingsService.createBooking(trxVo), HttpStatus.CREATED);

    }

    /* The post mapping call with the booking id to the transaction service
    *  first check for a valid booking id. If the booking is valid, only then
    * it generates the transaction, by making a call to the payments service.
    * If the booking is not found it returns and error to the user saying booking not found.
    * Once the transaction is generated, it log the information in the console.
    * */
    @PostMapping("/booking/{id}/transaction")
    public ResponseEntity getBookingStatus(@Valid @RequestBody Transaction trxVo, @PathVariable Integer id) {

        Booking bookingEntry = bookingsService.getBookingStatus(id).orElseThrow(() -> new BookingNotFoundException("Booking not found for the given id "+id));

        if(bookingEntry.getBookingId()!=null){
            System.out.println("Booking id found "+bookingEntry.getBookingId() );
            bookingEntry =  bookingsService.createTrx(trxVo, id,bookingEntry);
        }

        logger.info("Booking confirmed for user with aadhaar number: "
                + bookingEntry.getAadharNumber()
                +    "    |    "
                + "Here are the booking details:    "+ bookingEntry.toString());
        return ResponseEntity.ok(bookingEntry);

    }

    @GetMapping("/booking/{id}")
    public Booking createBooking(@PathVariable Integer id) {
        Optional<Booking> response = bookingsService.getBookingStatus(id);

        return  response.orElseThrow(()-> new RecordNotFoundException("Booking not generated for the id :"+id));


    }
}