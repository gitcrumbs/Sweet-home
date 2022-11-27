package com.example.payments.Service;


import com.example.payments.Repository.TransactionRepository;
import com.example.payments.entities.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Value("${bookingApp.url}")
    private String bookingAppurl;
    
    private TransactionRepository trxRepository;
    public TransactionService(TransactionRepository trxRepository){
        this.trxRepository=trxRepository;

    }

    public int createTransaction(Transaction trx){
        return trxRepository.save(trx).getTransactionId();
    }
    public Optional<Transaction> getTransactionStatus(int bookingId){
        return  trxRepository.findById(bookingId);
    }
}