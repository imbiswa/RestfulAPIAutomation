package org.thetestingacademy.modules;

import com.google.gson.Gson;
import org.thetestingacademy.Pojos.*;
import com.github.javafaker.Faker;

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


        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);
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


       BookingDates bookdate = new BookingDates();
        bookdate.setCheckin("2024-01-01");
        bookdate.setCheckout("2024-02-01");

        booking.setBookingdates(bookdate);
        booking.setAdditionalneeds("snacks");
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

    public String setAuthPayLoad()
    {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson=new Gson();
        String gettokenPayload = gson.toJson(auth);
        System.out.println("set to the"+ gettokenPayload);
        return  gettokenPayload;
    }

    public String getTokeFromJson(String tokenResponse)
    {
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public Booking getResponseFromJSON(String getResponse)
    {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;

    }
    public String fullUpdatePayloadAsString()
    {
        Booking booking = new Booking ();
        booking.setFirstname("Biswajit");
        booking.setLastname("Mallick");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Lunch");

        return  gson.toJson(booking);
    }
}
