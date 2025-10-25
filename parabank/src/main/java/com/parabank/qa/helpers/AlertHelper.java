package com.parabank.qa.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.config.RuntimeManager;

public class AlertHelper {

    private WebDriver driver;

    public AlertHelper() {
        this.driver = RuntimeManager.getDriver();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void sendTextToAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }
}
