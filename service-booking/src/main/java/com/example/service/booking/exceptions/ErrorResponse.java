package com.example.service.booking.exceptions;

import java.util.List;

public class ErrorResponse {

    private  String errorMessage;
    private List<String> errorDetails;

    public ErrorResponse(String errorMessage, List<String> errorDetails) {
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
