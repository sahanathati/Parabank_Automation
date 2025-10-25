package com.parabank.qa.stepdefinitions;

import com.parabank.qa.helpers.AssertionHelper;
import com.parabank.qa.pages.AccountOverviewPage;
import com.parabank.qa.helpers.WaitHelper;
import com.parabank.qa.helpers.ScreenshotUtil;
import com.parabank.qa.listeners.ExtentTestManager;

import io.cucumber.java.en.Then;

public class AccountServicesSteps {

    private final AccountOverviewPage accountOverviewPage = new AccountOverviewPage();

    @Then("account overview section should be displayed")
    public void account_overview_section_should_be_displayed() {
        boolean isVisible = accountOverviewPage.isAccountOverviewHeaderVisible();
        try {
            AssertionHelper.verifyTrue(isVisible, "Account Overview section is visible");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.captureScreenshot("overviewNotVisible");
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
            throw e;
        }
    }

    @Then("account summary table should contain at least one account")
    public void account_summary_table_should_contain_at_least_one_account() {
        int accountCount = accountOverviewPage.getAccountRowCount();
        try {
            AssertionHelper.verifyTrue(accountCount > 0, "At least one account is listed in summary");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.captureScreenshot("noAccountsVisible");
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
            throw e;
        }
    }
}

