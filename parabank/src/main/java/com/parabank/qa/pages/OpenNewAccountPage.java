package com.parabank.qa.pages;

import com.parabank.qa.helpers.ElementHelper;
import com.parabank.qa.config.RuntimeManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage {

    private ElementHelper elementHelper = new ElementHelper();

    private final By accountTypeDropdown = By.id("type");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By openNewAccountButton = By.xpath("//input[@value='Open New Account']");

    public void selectAccountType(String accountType) {
        new Select(RuntimeManager.getDriver().findElement(accountTypeDropdown)).selectByVisibleText(accountType);
    }

    public void selectFromAccount(String accountNumber) {
        new Select(RuntimeManager.getDriver().findElement(fromAccountDropdown)).selectByVisibleText(accountNumber);
    }

    public void clickOpenNewAccount() {
        RuntimeManager.getDriver().findElement(openNewAccountButton).click();
    }
}
