package com.parabank.qa.stepdefinitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.parabank.qa.config.RuntimeManager;
import com.parabank.qa.helpers.AlertHelper;
import com.parabank.qa.helpers.AssertionHelper;
import com.parabank.qa.helpers.WaitHelper;
import com.parabank.qa.pages.AccountOverviewPage;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.OpenNewAccountPage;
import com.parabank.qa.pages.TransferFundsPage;
import com.parabank.qa.utils.LogHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransferFundsSteps {

	private static final Logger log = LogHelper.getLogger(TransferFundsSteps.class);
    private LoginPage loginPage;
    private AccountOverviewPage accountOverviewPage;
    private OpenNewAccountPage openNewAccountPage;
    private TransferFundsPage transferFundsPage;

    private String fromAccount;
    private String toAccount;

    @Given("user is logged into Parabank with username {string} and password {string}")
    public void user_is_logged_in(String username, String password) {
        loginPage = new LoginPage();
        RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        boolean loggedIn = new WaitHelper().waitForTitleToBe("ParaBank | Accounts Overview");
        AssertionHelper.verifyTrue(loggedIn, "User should be logged in and land on Accounts Overview page");
    }

    @Given("the user has at least two accounts")
    public void user_has_at_least_two_accounts() {
        accountOverviewPage = new AccountOverviewPage();

        // Navigate to Accounts Overview page explicitly (if not already there)
        RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/overview.htm");

        List<String> accounts = accountOverviewPage.getAccountNumbers();

        if (accounts.size() < 2) {
            openNewAccountPage = new OpenNewAccountPage();

            // Navigate to Open New Account page
            RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/openaccount.htm");

            openNewAccountPage.selectAccountType("CHECKING"); // or "SAVINGS"
            openNewAccountPage.selectFromAccount(accounts.get(0));
            openNewAccountPage.clickOpenNewAccount();

            // After opening, refresh Accounts Overview page and get accounts again
            RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/overview.htm");
            accounts = accountOverviewPage.getAccountNumbers();
        }

        // Store first two account numbers for transfer
        fromAccount = accounts.get(0);
        toAccount = accounts.get(1);
        log.info("fromAccount "+fromAccount);
        log.info("toAccount "+toAccount);
    }

    @When("the user transfers {string} between their accounts")
    public void user_transfers_between_accounts(String amount) throws InterruptedException {
        transferFundsPage = new TransferFundsPage();

        // Navigate to Transfer Funds page
        RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/transfer.htm");

        transferFundsPage.enterAmount(amount);
        transferFundsPage.selectFromAccount(fromAccount);
        transferFundsPage.selectToAccount(toAccount);
        transferFundsPage.clickTransfer();
        log.info("transferred amount from  "+fromAccount+"to account "+toAccount+"with amount of $");
        
    }

    @Then("a transfer confirmation message should be displayed")
    public void transfer_confirmation_message_should_be_displayed() {
    	log.info("In method 'Then a transfer confirmation message should be displayed'");
        String message = transferFundsPage.getConfirmationMessage();
        log.info("Confirmation message received: '" + message + "'");
        AssertionHelper.verifyTrue(message.contains("Transfer Complete!"), "Confirmation message should contain 'Transfer Complete!'");
        log.info("End method 'Then a transfer confirmation message should be displayed'");
    }


}
