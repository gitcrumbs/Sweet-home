package com.example.service.booking.Dao;

import com.example.service.booking.entities.Booking;

public interface BookingsDao {

    public Booking save(Booking bookings);
    public Booking findbyId(int  bookingId);
    public Booking update(Booking booking);
    public void delete(Booking booking);

}


