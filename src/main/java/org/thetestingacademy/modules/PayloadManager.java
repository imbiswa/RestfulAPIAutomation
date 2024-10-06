package org.thetestingacademy.modules;

import com.google.gson.Gson;
import org.thetestingacademy.Pojos.Booking;
import com.github.javafaker.Faker;
import org.thetestingacademy.Pojos.BookingResponse;

public class PayloadManager {
    //Ser and Dser
    Gson gson;

//converts a simple java object to jsonpayload-String
    public String createpayloadasStringPost()

    {

        Booking booking = new Booking();
        booking.setFirstname("Biswajit");
        booking.setLastname("Mallick");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);


        com.thetestingacademy.pojos.BookingDates bookdate = new com.thetestingacademy.pojos.BookingDates();
        bookdate.setCheckin("2024-01-01");
        bookdate.setCheckout("2024-02-01");

        booking.setBookingdates(bookdate);
        booking.setAdditonalneeds("Snacks");
        //now Pojo or java object needs to ->convert to JSON string (byteStream) - Serialization
        Gson gson = new Gson();
         String post_payload = gson.toJson(booking);
        return post_payload;
    }
    public String createpayloadasStringPostFaker()

    {
        Faker fake = new Faker();


        Booking booking = new Booking();
        booking.setFirstname(fake.name().firstName());
        booking.setLastname(fake.name().lastName());
        booking.setTotalprice(fake.random().nextInt(100));
        booking.setDepositpaid(true);


        com.thetestingacademy.pojos.BookingDates bookdate = new com.thetestingacademy.pojos.BookingDates();
        bookdate.setCheckin("2024-01-01");
        bookdate.setCheckout("2024-02-01");

        booking.setBookingdates(bookdate);
        booking.setAdditonalneeds("Snacks");
        //now Pojo or java object needs to ->convert to JSON string (byteStream) - Serialization
        Gson gson = new Gson();
        String post_payload = gson.toJson(booking);
        return post_payload;
    }

    public BookingResponse bookingResponseJava (String responseString)
    {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;


    }
}
