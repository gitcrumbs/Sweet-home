package com.example.payments.Dao;

import com.example.payments.entities.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class TransactionDaoImpl implements TransactionDao{

    private SessionFactory sessionFactory ;

    @Autowired
    public TransactionDaoImpl(EntityManagerFactory entityManagerFactory){
        this.sessionFactory=entityManagerFactory.unwrap(SessionFactory.class);
    }
    @Override
    public Transaction save(Transaction transactions) {


        Session session = this.sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        session.save(transactions);
        session.save(transactions);
        transaction.commit();
        session.close();
        return transactions;
    }

    @Override
    public Transaction findbyId(int bookingId) {
        Session session = this.sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Transaction booking = session.get(Transaction.class,bookingId);
        transaction.commit();
        session.close();

        return  booking;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public void delete(Transaction transaction) {

    }
}
