package com.example.payments.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NO_RECORDS_FOUND="NO_RECORDS_FOUND" ;

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e, WebRequest request) {

        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(NO_RECORDS_FOUND,errorDetails);
        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
       MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message","Invalid mode of payment");
        responseBody.put("statusCode",status.value());


        return new ResponseEntity<>(responseBody,headers,status);


    }


}