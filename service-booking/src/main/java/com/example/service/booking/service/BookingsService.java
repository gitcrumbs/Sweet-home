package com.example.service.booking.service;

import com.example.service.booking.entities.Booking;
import com.example.service.booking.entities.Transaction;
import com.example.service.booking.exceptions.RecordNotFoundException;
import com.example.service.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
            Calendar cl = Calendar. getInstance();
            Timestamp filterDateFromTs = null,filterDateToTs=null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date firstDate = dateFormat.parse(booking.getFromDate());
            Date secondDate = dateFormat.parse(booking.getToDate());
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            long roomPrice = 1000 * numOfRooms*(daysBetween);
            String timeStampFrom =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format((dateFormat.parse(booking.getFromDate())).getTime());
            String timeStampTo =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format((dateFormat.parse(booking.getToDate())).getTime());
            booking.setFromDate(timeStampFrom);
            booking.setToDate(timeStampTo);
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

        Booking responseBooking= bookingRepository.findById(result.getBookingId()).orElseThrow(()-> new RecordNotFoundException("User with ID :"+id+" Not Found!"));
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
