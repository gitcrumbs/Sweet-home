package com.example.paymentsservice.Dao;

import com.example.paymentsservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction,Integer> {


}
