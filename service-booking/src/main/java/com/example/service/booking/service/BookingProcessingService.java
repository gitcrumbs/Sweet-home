package com.example.service.booking.service;

import com.example.service.booking.Dao.BookingsDao;
import com.example.service.booking.entities.Booking;
import com.example.service.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingProcessingService {

    private BookingRepository repository;

    public BookingProcessingService(BookingRepository repository){
        this.repository=repository;
    }

    public Booking createBooking(Booking bookings){
        bookings.setTransactionId(String.valueOf(UUID.randomUUID().toString()));
       return repository.save(bookings);
    }
}
