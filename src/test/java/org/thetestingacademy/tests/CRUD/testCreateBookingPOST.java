package org.thetestingacademy.tests.CRUD;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.thetestingacademy.Pojos.BookingResponse;
import org.thetestingacademy.base.BaseTest;
import org.thetestingacademy.endpoints.APIConstants;
import org.thetestingacademy.utils.PropertyReader;
//import com.thetestingacademy.utils.PropertyReader;
import static org.assertj.core.api.Assertions.*;


import static org.assertj.core.api.Assertions.assertThat;

public class testCreateBookingPOST extends BaseTest {

    @Owner("Biswajit")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Jira link")
    @Description("Verify that post request for creating booking is working fine")
    public void test_verify_create_post()
    {
//here we are using only base path because , other setup already done in base url
        //so only calling basePath
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        //
        response = RestAssured.given(requestSpecification)
                        .when().body(payloadManager.createpayloadasStringPost())
                        .post();

        validatableResponse =response.then().log().all();

        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readKey("booking.post.statuscode.success")));

//Default Rest Assured
        validatableResponse.body("booking.firstname" , Matchers.equalTo("Biswajit"));

        //Assert j
        BookingResponse bookingResponse =payloadManager.bookingResponseJava((response.asString()));
        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty();
        //assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));

   //TestNG

        assertActions.verifyStatusCode(response,200);

       // Assert.assertEquals(true,true);
    }
}
