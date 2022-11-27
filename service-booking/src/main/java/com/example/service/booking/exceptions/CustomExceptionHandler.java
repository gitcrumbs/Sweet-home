package com.example.service.booking.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NO_RECORDS_FOUND="NO_RECORDS_FOUND" ;

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        System.out.println("Exception is " +ex);

        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message","Invalid mode of payment");
        responseBody.put("statusCode",status.value());


        return new ResponseEntity<>(responseBody,headers,status);


    }

}