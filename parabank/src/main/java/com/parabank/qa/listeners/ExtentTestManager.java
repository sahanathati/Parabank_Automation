package com.parabank.qa.listeners;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    // Thread-safe storage for each test thread's ExtentTest instance
    private static final ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTestThread.set(test);
    }

    public static ExtentTest getTest() {
        return extentTestThread.get();
    }

    public static void unload() {
        extentTestThread.remove();
    }
}
