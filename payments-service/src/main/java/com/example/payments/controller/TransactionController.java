package com.example.payments.controller;


import com.example.payments.Service.TransactionService;
import com.example.payments.entities.Transaction;

import com.example.payments.entities.TransactionConfirmation;
import com.example.payments.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment/")
public class TransactionController {

    TransactionService trxService;
    public  TransactionController(TransactionService trxService){
        this.trxService=trxService;
    }

    @GetMapping("/transaction/{bookingId}")
    public Transaction getOrder(@PathVariable Integer bookingId){
        Optional<Transaction>response=trxService.getTransactionStatus(bookingId);
        return response.orElseThrow(()->new RecordNotFoundException("Transaction for the Booking id :"+bookingId+"Not Generated"));

    }

    @PostMapping("/transaction")
    public ResponseEntity createTransaction(@RequestBody Transaction trxVo){

        TransactionConfirmation created = trxService.createTransaction(trxVo);

        created.getBookingDetails().setTransactionId(created.getTrxBooking().getTransactionId());

        created.setMessage("Booking confirmed for user with aadhaar number: "
                + created.getBookingDetails().getAadharNumber()
                +    "    |    "
                + "Here are the booking details:    "+ created.getBookingDetails().toString());

        return ResponseEntity.ok(created.getBookingDetails());

    }
}