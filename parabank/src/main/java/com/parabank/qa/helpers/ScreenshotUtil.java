package com.parabank.qa.helpers;

import com.parabank.qa.config.RuntimeManager;
import com.parabank.qa.utils.LogHelper;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final Logger log = LogHelper.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(String testName) {
        WebDriver driver = RuntimeManager.getDriver();

        if (driver == null) {
            log.error("WebDriver instance is null. Cannot take screenshot.");
            return null;
        }

        try {
            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Build screenshot folder path
            String screenshotsDir = LogHelper.getCurrentRunPath() + "/screenshots";
            File folder = new File(screenshotsDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Format filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String fullPath = screenshotsDir + "/" + fileName;

            // Save file
            File dest = new File(fullPath);
            FileUtils.copyFile(src, dest);

            log.info("📸 Screenshot captured: " + fullPath);
            return fullPath;

        } catch (Exception e) {
            log.error("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            log.error("WebDriver instance is null. Cannot take screenshot.");
            return null;
        }

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotsDir = LogHelper.getCurrentRunPath() + "/screenshots";
            File folder = new File(screenshotsDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName.replaceAll("[^a-zA-Z0-9-_]", "_") + "_" + timestamp + ".png";
            String fullPath = screenshotsDir + "/" + fileName;

            File dest = new File(fullPath);
            FileUtils.copyFile(src, dest);
            log.info("📸 Screenshot captured: " + fullPath);
            return fullPath;

        } catch (Exception e) {
            log.error("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
