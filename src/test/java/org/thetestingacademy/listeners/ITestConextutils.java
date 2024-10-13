package org.thetestingacademy.listeners;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

public class ITestConextutils {

    private static ITestContext context;

    public static void setTestContext(ITestContext iTestContext) {
        context = iTestContext;
    }

    public static ITestContext getTestContext() {
        return context;
    }

    public static Object getAttribute(String name) {
        return context.getAttribute(name);
    }

    public static void setAttribute(String name, Object value) {
        context.setAttribute(name, value);

    }


}
