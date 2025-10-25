package com.parabank.qa.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.parabank.qa.config.ConfigReader;
import com.parabank.qa.config.RuntimeManager;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (ConfigReader.get("headless").equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }
        // Add other browsers here

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(ConfigReader.get("implicitWait"))));

        RuntimeManager.setDriver(driver);
        return driver;
    }
}

