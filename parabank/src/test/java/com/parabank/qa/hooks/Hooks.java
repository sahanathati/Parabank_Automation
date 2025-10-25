package com.parabank.qa.hooks;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.parabank.qa.config.ConfigReader;
import com.parabank.qa.config.RuntimeManager;
import com.parabank.qa.drivers.DriverFactory;
import com.parabank.qa.helpers.ScreenshotUtil;
import com.parabank.qa.listeners.ExtentManager;
import com.parabank.qa.listeners.ExtentTestManager;
import com.parabank.qa.utils.LogHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger log = LogHelper.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("🔧 [Before] Starting driver setup for scenario");
        ConfigReader.loadProperties();
        WebDriver driver = DriverFactory.initDriver(ConfigReader.get("browser"));
        RuntimeManager.setDriver(driver);
        String baseUrl = ConfigReader.get("baseUrl");
        log.info("🌐 Navigating to URL: " + baseUrl);
        driver.get(baseUrl);  // <-- Navigate here
        
     // -------- Add ExtentReports integration here --------
        ExtentTest test = ExtentManager.getInstance().createTest(scenario.getName());
        ExtentTestManager.setTest(test);
        log.info("📝 ExtentTest started for scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("🧹 [After] Cleaning up driver after scenario");
        
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            if (scenario.isFailed()) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(scenario.getName());
                test.fail("Scenario Failed: " + scenario.getName());
                if (screenshotPath != null) {
                    test.addScreenCaptureFromPath(screenshotPath);
                    test.fail("📸 Screenshot attached for failure.");
                }
            } else {
                test.pass("Scenario Passed: " + scenario.getName());
            }
        }
        
        
        WebDriver driver = RuntimeManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
        RuntimeManager.unload();
    }
}
