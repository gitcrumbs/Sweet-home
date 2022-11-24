package com.example.payments.entities;


public class TransactionConfirmation {

    String message;

    Booking bookingDetails;

    public Transaction getTrxBooking() {
        return trxBooking;
    }

    public void setTrxBooking(Transaction trxBooking) {
        this.trxBooking = trxBooking;
    }

    Transaction trxBooking;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Booking getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Booking bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    @Override
    public String toString() {
        return "TransactionConfirmation{" +
                "message='" + message + '\'' +
                ", bookingDetails=" + bookingDetails +
                '}';
    }
}
