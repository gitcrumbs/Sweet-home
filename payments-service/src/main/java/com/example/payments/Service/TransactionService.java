package com.example.payments.Service;


import com.example.payments.Repository.TransactionRepository;
import com.example.payments.entities.Booking;
import com.example.payments.entities.Transaction;
import com.example.payments.entities.TransactionConfirmation;
import com.example.payments.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository trxRepository;
    public TransactionService(TransactionRepository trxRepository){
        this.trxRepository=trxRepository;

    }

    public TransactionConfirmation createTransaction(Transaction trx){
        Booking bookingentry = new Booking();
        TransactionConfirmation conf = new TransactionConfirmation();
        try{
            RestTemplate restTemplate = new RestTemplate();
            bookingentry = restTemplate.getForObject("http://localhost:8080/hotel/booking/"+trx.getBookingId(),  Booking.class);


            if(bookingentry.getBookingId()!=null){                               ;
                conf.setTrxBooking(trxRepository.save(trx));
                conf.setBookingDetails(bookingentry);
                return conf;
            }else{
                throw  new RecordNotFoundException("Booking Id :"+trx.getBookingId()+" is not Found");
            }

        }catch(Exception e){
            throw  new RecordNotFoundException("Booking Id :"+trx.getBookingId()+" is not Found");
        }



    }
    public Optional<Transaction> getTransactionStatus(int bookingId){
        return  trxRepository.findById(bookingId);
    }
}