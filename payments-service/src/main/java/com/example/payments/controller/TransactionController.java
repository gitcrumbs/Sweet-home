package com.example.payments.controller;


import com.example.payments.Service.TransactionService;
import com.example.payments.entities.Transaction;

import com.example.payments.exceptions.RecordNotFoundException;


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
@RequestMapping("/payment/")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);
    TransactionService trxService;
    public  TransactionController(TransactionService trxService){
        this.trxService=trxService;
    }

    @GetMapping("/transaction/{transactionId}")
    public Transaction getOrder(@PathVariable Integer transactionId){
        Optional<Transaction>response=trxService.getTransactionStatus(transactionId);
        return response.orElseThrow(()->new RecordNotFoundException("No Transaction Information found for :"+transactionId));

    }

    @PostMapping("/transaction")
    public ResponseEntity<Integer> createTransaction(@Valid @RequestBody Transaction trxVo){

        Integer created = trxService.createTransaction(trxVo);
        return new ResponseEntity<>(created, HttpStatus.CREATED);


    }
}