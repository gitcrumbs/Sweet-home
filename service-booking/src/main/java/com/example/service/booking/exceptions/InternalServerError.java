package com.example.service.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class InternalServerError extends HttpServerErrorException {

    private static final long serialVersionUID = 1L;
    public InternalServerError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
