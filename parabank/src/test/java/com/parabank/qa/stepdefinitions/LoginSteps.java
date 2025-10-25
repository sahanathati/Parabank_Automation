package com.parabank.qa.stepdefinitions;

import com.parabank.qa.config.RuntimeManager;
import com.parabank.qa.helpers.AssertionHelper;
import com.parabank.qa.helpers.ScreenshotUtil;
import com.parabank.qa.helpers.WaitHelper;
import com.parabank.qa.listeners.ExtentTestManager;
import com.parabank.qa.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	private LoginPage loginPage;

	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		//RuntimeManager.getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
		loginPage = new LoginPage();
		boolean isCorrectTitle = new WaitHelper().waitForTitleToBe("ParaBank | Welcome | Online Banking");
        String actualTitle = RuntimeManager.getDriver().getTitle();

        ExtentTestManager.getTest().info("🔍 Actual title: " + actualTitle);

        try {
            AssertionHelper.verifyTrue(isCorrectTitle, "User is on login page");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.captureScreenshot("loginPageTitleMismatch");
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
            throw e;
        }
	}
	
	@When("user enters username {string} and password {string}")
	public void user_enters_username_and_password(String username, String password) {
	   loginPage.enterUsername(username); 
	   loginPage.enterPassword(password);
	   ExtentTestManager.getTest().info(" Entered credentials: " + username + " / " + password);
	   
	}
	@When("clicks on the login button")
	public void clicks_on_the_login_button() {
	    loginPage.clickLogin();
	    ExtentTestManager.getTest().info("👉 Clicked Login button");
	   
	}
	@Then("user should land on {string}")
	 public void user_should_land_on(String expectedTitle) {
        boolean isExpectedTitle = new WaitHelper().waitForTitleToBe("ParaBank | " + expectedTitle);
        String actualTitle = RuntimeManager.getDriver().getTitle();

        ExtentTestManager.getTest().info("🧭 Actual post-login title: " + actualTitle);

        try {
            AssertionHelper.verifyTrue(isExpectedTitle, "User landed on expected page: " + expectedTitle);
        } catch (AssertionError e) {
            String path = ScreenshotUtil.captureScreenshot("loginSuccessMismatch");
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
            throw e;
        }

	}

}
