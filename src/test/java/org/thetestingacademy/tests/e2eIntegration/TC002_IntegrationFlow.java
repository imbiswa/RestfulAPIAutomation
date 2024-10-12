package org.thetestingacademy.tests.e2eIntegration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.thetestingacademy.Pojos.BookingResponse;
import org.thetestingacademy.base.BaseTest;
import org.thetestingacademy.endpoints.APIConstants;

import static org.assertj.core.api.Assertions.assertThat;

public class TC002_IntegrationFlow extends BaseTest {

    //create a booking
    //delete the booking
    //get the booking and verify

    @Test(groups = "integration",priority = 1)
    @Owner("Biswajit")
    @Description("TC001:Verify that user is able to create a booking")
    public void verifyCreateBooking(ITestContext iTestContext)
    {
       iTestContext.setAttribute("token", getToken());


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadasStringPost()).post();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);





        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isEqualTo("Biswajit");
        assertThat(bookingResponse.getBooking().getLastname()).isNotEmpty().isEqualTo("Mallick");
        System.out.println("bookingid that created"+bookingResponse.getBookingid());
        iTestContext.setAttribute("bookingid" ,bookingResponse.getBookingid());
    }
    @Test(groups = "integration",priority = 2)
    @Owner("Biswajit")
    @Description("TC002:Verify that user is able to delete a booking")
    public void verifyDeleteBooking(ITestContext iTestContext)
    {


        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid =(Integer) iTestContext.getAttribute("bookingid");
        System.out.println("bookingid that deleting"+bookingid);

        String deletepath = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(deletepath);

        response=RestAssured.given(requestSpecification).cookie("token", token).when().delete();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(201);


    }
    @Test(groups = "integration",priority = 3)
    @Owner("Biswajit")
    @Description("TC003:Verify that bookingid is not present after delete")
    public void verifyGetDeletedBooking(ITestContext iTestContext)
    {
        String token = (String) iTestContext.getAttribute("token");

        Integer bookingid =(Integer) iTestContext.getAttribute("bookingid");


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);

        response=RestAssured.given(requestSpecification).when().get();

       validatableResponse = response.then().log().all();
       validatableResponse.statusCode(404);

    }

}
