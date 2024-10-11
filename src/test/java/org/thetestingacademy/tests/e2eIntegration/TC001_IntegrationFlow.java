package org.thetestingacademy.tests.e2eIntegration;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.thetestingacademy.Pojos.Booking;
import org.thetestingacademy.Pojos.BookingResponse;
import org.thetestingacademy.base.BaseTest;
import org.thetestingacademy.endpoints.APIConstants;
import org.thetestingacademy.utils.PropertyReader;
import static org.assertj.core.api.Assertions.assertThat;

public class TC001_IntegrationFlow extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Biswajit")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());
        System.out.println("printing payload");
        System.out.println(payloadManager.createpayloadasStringPost());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createpayloadasStringPost()).post();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);
//        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));

        // DeSer
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
       assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        //  Set the booking ID
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Biswajit")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer)iTestContext.getAttribute("bookingid");
        String basepathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid ;
        requestSpecification.basePath(basepathGET);
          response =RestAssured.given(requestSpecification).basePath(basepathGET)
                 .when().get();
          validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));

    }

    @Test(groups = "integration", priority = 3)
    @Owner("Biswajit")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basepathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basepathPUTPATCH);

        requestSpecification.basePath(basepathPUTPATCH);

        response=RestAssured.given(requestSpecification).cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotEmpty().isNotNull().isEqualTo("Biswajit");
        assertThat(booking.getLastname()).isNotEmpty().isNotNull().isEqualTo("Mallick");


    }

    @Test(groups = "integration", priority = 4)
    @Owner("Biswajit")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext)
    {
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid =(Integer) iTestContext.getAttribute("bookingid");

        String basepathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(basepathDELETE);

         response=RestAssured.given(requestSpecification).cookie("token", token).when().delete();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(201);







    }


}