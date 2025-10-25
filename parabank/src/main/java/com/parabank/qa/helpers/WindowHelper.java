package com.parabank.qa.helpers;

import org.openqa.selenium.WebDriver;

import com.parabank.qa.config.RuntimeManager;

import java.util.Set;

public class WindowHelper {

    private WebDriver driver;

    public WindowHelper() {
        this.driver = RuntimeManager.getDriver();
    }

    // Switch to the newly opened window (last handle)
    public void switchToNewWindow() {
        Set<String> handles = driver.getWindowHandles();
        for (String window : handles) {
            driver.switchTo().window(window);
        }
    }

    // Switch to window by title
    public void switchToWindowWithTitle(String title) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    // Close all tabs except the parent one
    public void closeAllTabsExceptParent() {
        String parent = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    // Get current window title
    public String getWindowTitle() {
        return driver.getTitle();
    }
}
