package com.example.payments.controller;

import com.example.payments.Service.TransactionService;
import com.example.payments.entities.Transaction;

import com.example.payments.entities.TransactionConfirmation;
import net.bytebuddy.asm.Advice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/")
public class TransactionController {

    TransactionService trxService;
    public  TransactionController(TransactionService trxService){
        this.trxService=trxService;
    }

    @GetMapping("/transaction/{bookingId}")
    public ResponseEntity getOrder(@PathVariable Integer bookingId){

        return ResponseEntity.ok(trxService.getTransactionStatus(bookingId));

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
