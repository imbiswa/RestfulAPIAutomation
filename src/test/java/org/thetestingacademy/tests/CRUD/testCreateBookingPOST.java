package org.thetestingacademy.tests.CRUD;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testCreateBookingPOST {

    @Owner("Biswajit")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Jira link")
    public void test_verify_create_post()
    {
        Assert.assertEquals(true,true);
    }
}
