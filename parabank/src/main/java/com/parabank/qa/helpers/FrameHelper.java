package com.parabank.qa.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.parabank.qa.config.RuntimeManager;

public class FrameHelper {

    private WebDriver driver;

    public FrameHelper() {
        this.driver = RuntimeManager.getDriver();
    }

    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrameByName(String name) {
        driver.switchTo().frame(name);
    }

    public void switchToFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }
}

