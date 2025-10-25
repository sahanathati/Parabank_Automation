package com.parabank.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parabank.qa.helpers.ScreenshotUtil;
import com.parabank.qa.utils.LogHelper;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports extent;
    private static final Logger log = LogHelper.getLogger(ExtentReportListener.class);

    static {
        // ✅ Force logs directory to be created before extent starts
        LogHelper.getLogger(ExtentReportListener.class);
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        //ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        //ExtentTestManager.setTest(test); // ✅ Store in ThreadLocal via ExtentTestManager
        log.info("✅ -----Test Started-----: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.pass("✅ Test Passed");
        }
        log.info("✅ ----Test Passed-----: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        log.error("❌---- Test Failed----: " + methodName, result.getThrowable());

        try {
            String screenshotPath = ScreenshotUtil.captureScreenshot(methodName);
            ExtentTest test = ExtentTestManager.getTest();

            if (test != null) {
                test.fail(result.getThrowable());

                if (screenshotPath != null) {
                    test.addScreenCaptureFromPath(screenshotPath);
                    test.log(Status.FAIL, "📸 Screenshot saved at: " + screenshotPath);
                } else {
                    test.log(Status.WARNING, "⚠️ Screenshot could not be captured.");
                }
            } else {
                log.warn("⚠️ ExtentTest instance was null during onTestFailure.");
            }

        } catch (Exception e) {
            log.error("❌ Exception in onTestFailure: " + e.getMessage(), e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.skip(result.getThrowable());
        }
        log.warn("⚠️------ Test Skipped-------: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        ExtentTestManager.unload(); // ✅ Optional cleanup
        log.info("📄 Extent Report Flushed and Finalized.");
    }
}
