package com.parabank.qa.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.parabank.qa.config.RuntimeManager;

public class ElementHelper {

    private WebDriver driver;

    public ElementHelper() {
        this.driver = RuntimeManager.getDriver();
    }

    public void click(WebElement element) {
        new WaitHelper().waitForElementClickable(element).click();
    }

    public void sendText(WebElement element, String text) {
        WebElement e = new WaitHelper().waitForElementVisible(element);
        e.clear();
        e.sendKeys(text);
    }

    public String getText(WebElement element) {
        return new WaitHelper().waitForElementVisible(element).getText();
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return new WaitHelper().waitForElementVisible(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    
}

