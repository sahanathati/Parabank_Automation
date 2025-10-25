package com.parabank.qa.pages;

import com.parabank.qa.helpers.ElementHelper;
import com.parabank.qa.helpers.WaitHelper;
import com.parabank.qa.config.RuntimeManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AccountOverviewPage {

    private final By accountOverviewHeader = By.xpath("//h1[normalize-space()='Accounts Overview']");
    private final By accountTableRows = By.cssSelector("table[id='accountTable'] tbody tr");
    private final By accountLinks = By.cssSelector("table[id='accountTable'] tbody tr td a");

    public boolean isAccountOverviewHeaderVisible() {
        return new WaitHelper().waitForElementVisible(accountOverviewHeader).isDisplayed();
    }

    public int getAccountRowCount() {
        List<WebElement> rows = RuntimeManager.getDriver().findElements(accountTableRows);
        return rows.size();
    }
    
    public List<String> getAccountNumbers() {
        List<WebElement> accounts = RuntimeManager.getDriver().findElements(accountLinks);
        return accounts.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
