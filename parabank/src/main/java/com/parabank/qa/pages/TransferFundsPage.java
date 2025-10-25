package com.parabank.qa.pages;

import com.parabank.qa.helpers.ElementHelper;
import com.parabank.qa.config.RuntimeManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {

    private ElementHelper elementHelper = new ElementHelper();

    private final By amountInput = By.id("amount");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By toAccountDropdown = By.id("toAccountId");
    private final By transferButton = By.xpath("//input[@value='Transfer']");
    private final By confirmationMessage = By.cssSelector("#rightPanel h1");
    private final By transferAmountLabel = By.id("amount");

    public void enterAmount(String amount) {
        RuntimeManager.getDriver().findElement(amountInput).sendKeys(amount);
    }

    public void selectFromAccount(String fromAccount) {
        new Select(RuntimeManager.getDriver().findElement(fromAccountDropdown)).selectByVisibleText(fromAccount);
    }

    public void selectToAccount(String toAccount) {
        new Select(RuntimeManager.getDriver().findElement(toAccountDropdown)).selectByVisibleText(toAccount);
    }

    public void clickTransfer() throws InterruptedException {
    	
        RuntimeManager.getDriver().findElement(transferButton).click();
        System.out.println("clicked transfer button");
        Thread.sleep(1000);
    }

    public String getConfirmationMessage() {
        return RuntimeManager.getDriver().findElement(confirmationMessage).getText();
    }

    public String getTransferredAmount() {
        return RuntimeManager.getDriver().findElement(transferAmountLabel).getText();
    }
}
