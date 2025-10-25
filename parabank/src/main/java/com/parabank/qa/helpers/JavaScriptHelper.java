package com.parabank.qa.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.parabank.qa.config.RuntimeManager;


/**
 * Utility class for JavaScript and UI-level DOM interactions.
 */
public class JavaScriptHelper {

    private final JavascriptExecutor js;
    private final WebDriver driver;

    public JavaScriptHelper() {
        this.driver = RuntimeManager.getDriver();
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * Performs click using JavaScript Executor.
     */
    public void clickUsingJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls the element into view using JavaScript.
     */
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Highlights the element by drawing a red border.
     */
    public void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * Sets the value of an input field using JavaScript.
     */
    public void setValue(WebElement element, String value) {
        js.executeScript("arguments[0].value='" + value + "';", element);
    }
}