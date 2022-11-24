package com.example.service.booking.service;

import com.example.service.booking.entities.Booking;
import com.example.service.booking.entities.Transaction;
import com.example.service.booking.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingsService {

    @Autowired
    private RestTemplate restTemplate ;


    private BookingRepository bookingRepository;

    public BookingsService(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;

    }

    public Booking createBooking(Booking booking){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");



        int numOfRooms = booking.getNumOfRooms();
       // booking.setBookedOn(sdf2.format(timestamp));

       String s= getRandomNumbers(numOfRooms).toString();
        String requiredString = s.substring(s.indexOf("[")+1 , s.indexOf("]"));
        booking.setRoomNumbers(requiredString);
    try{
        booking.setBookedOn(LocalDateTime.now());
        LocalDateTime date1 = LocalDateTime.parse(booking.getFromDate(), dtf);
        LocalDateTime date2 = LocalDateTime.parse(booking.getToDate(), dtf);
        long daysBetween = Duration.between(date1, date2).toDays();



        long roomPrice = 1000 * numOfRooms*(daysBetween);
        booking.setRoomPrice((int)roomPrice);
    }catch(Exception e){
        System.out.println("Inside Exception "+e);
    }





      Booking bookedItem =  bookingRepository.save(booking);

        return bookedItem;
    }

    public Booking createTrx(Transaction trxVo, Integer id){
        //
        trxVo.setBookingId(id);
        RestTemplate restTemplate = new RestTemplate();
        Transaction result = restTemplate.postForObject("http://localhost:8081/payment/transaction", trxVo, Transaction.class);

        Booking responseBooking= bookingRepository.findById(result.getBookingId()).orElse(null);
        responseBooking.setTransactionId(result.getTransactionId());
        responseBooking.setBookedOn(LocalDateTime.now());

        Calendar cl = Calendar. getInstance();
        Timestamp filterDateFromTs = null,filterDateToTs=null;
        try {
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             filterDateFromTs = new Timestamp ((dateFormat.parse(responseBooking.getFromDate())).getTime());
             filterDateToTs = new Timestamp ((dateFormat.parse(responseBooking.getToDate())).getTime());

        } catch (Exception e) {
            System.out.println("Parse Error "+e);
        }
        responseBooking.setFromDate(filterDateFromTs.toString());
        responseBooking.setToDate(filterDateToTs.toString());

        return responseBooking;
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
    public Optional<Booking> getBookingStatus(Integer id){
        return  bookingRepository.findById(id);
    }

    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)).trim());
        }

        return numberList;
    }
}
