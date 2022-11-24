package com.example.service.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NO_RECORDS_FOUND = "NO_RECORDS_FOUND" ;
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR" ;

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e, WebRequest request){
        List<String> errorDetails = new ArrayList<>();
         errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response =new ErrorResponse(NO_RECORDS_FOUND,errorDetails);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerError.class)
    public final ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerError e, WebRequest request){
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response =new ErrorResponse(INTERNAL_SERVER_ERROR,errorDetails);

        System.out.println("Inside Custom Internal Server Error ");
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
