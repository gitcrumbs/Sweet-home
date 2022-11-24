package com.example.payments.Dao;

import com.example.payments.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao {

    public Transaction save(Transaction bookings);
    public Transaction findbyId(int  bookingId);
    public Transaction update(Transaction transaction);
    public void delete(Transaction transaction);

}