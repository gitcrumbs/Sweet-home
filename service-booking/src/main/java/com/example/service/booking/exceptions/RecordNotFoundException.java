package com.example.service.booking.exceptions;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public RecordNotFoundException(String message) {
        super(message);
    }
}
