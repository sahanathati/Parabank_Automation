package com.parabank.qa.helpers;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.parabank.qa.listeners.ExtentTestManager;
import com.parabank.qa.utils.LogHelper;

public class AssertionHelper {

    private static final Logger log = LogHelper.getLogger(AssertionHelper.class);
    private static final SoftAssert softAssert = new SoftAssert();

    public static void verifyTrue(boolean condition, String message) {
        log.info("✅ Assert TRUE: " + message);
        ExtentTestManager.getTest().info("✅ Assert TRUE: " + message);
        Assert.assertTrue(condition, message);
    }

    public static void verifyFalse(boolean condition, String message) {
        log.info("✅ Assert FALSE: " + message);
        ExtentTestManager.getTest().info("✅ Assert FALSE: " + message);
        Assert.assertFalse(condition, message);
    }

    public static void verifyEquals(String actual, String expected, String message) {
        log.info("🔎 Assert EQUALS: " + message + " | Actual: " + actual + " | Expected: " + expected);
        ExtentTestManager.getTest().info("🔎 Assert EQUALS: " + message + " | Actual: " + actual + " | Expected: " + expected);
        Assert.assertEquals(actual, expected, message);
    }
    
    public static void softVerifyTrue(boolean condition, String message) {
        log.info("🧪 [SOFT] Assert TRUE: " + message);
        softAssert.assertTrue(condition, message);
    }

    public static void assertAll() {
        softAssert.assertAll(); // Should be called at the end of test
    }
}

