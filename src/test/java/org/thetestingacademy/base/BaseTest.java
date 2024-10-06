package org.thetestingacademy.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.thetestingacademy.asserts.AssertActions;
import org.thetestingacademy.endpoints.APIConstants;
import org.thetestingacademy.modules.PayloadManager;

import java.lang.reflect.Type;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;
    //TC-Header
    @BeforeTest
    public void setUp()
    {
        payloadManager =new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

    }
}
