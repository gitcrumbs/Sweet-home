package com.example.service.booking.Dao;

import com.example.service.booking.entities.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class BookingsDaoImpl implements BookingsDao{

    private SessionFactory sessionFactory ;

    @Autowired
    public BookingsDaoImpl(EntityManagerFactory entityManagerFactory){
        this.sessionFactory=entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public Booking save(Booking bookings) {

        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookings);
        session.save(bookings);
        transaction.commit();
        session.close();
        return bookings;
    }

    @Override
    public Booking findbyId(int bookingId) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Booking booking = session.get(Booking.class,bookingId);
        transaction.commit();
        session.close();

        return  booking;
    }

    @Override
    public Booking update(Booking booking) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(booking);
        transaction.commit();
        session.close();
        return  booking;
    }

    @Override
    public void delete(Booking booking) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Booking mergedbookings =(Booking) session.merge(booking);
        session.delete(mergedbookings);
        transaction.commit();
        session.close();

    }

}



