package com.example.paymentsservice;

import com.example.paymentsservice.Dao.TransactionDao;
import com.example.paymentsservice.entities.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PaymentsServiceApplication {

	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(PaymentsServiceApplication.class, args);


		//Crud Operations on Transactions

		TransactionDao trxDao = context.getBean(TransactionDao.class);

		System.out.println(trxDao);

		Transaction trx = new Transaction();

		trx.setBookingId(1234);
		trx.setPaymentMode("Card");
		trx.setTransactionId(1234);
		trx.setUpiId("12345@upi");
		trx.setCardNumber("1234567");

			Transaction savedTrx = trxDao.save(trx);


		System.out.println("After Saving"+savedTrx);
	}





}
