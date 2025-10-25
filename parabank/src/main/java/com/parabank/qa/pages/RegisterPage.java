package com.parabank.qa.pages;

import com.parabank.qa.helpers.ElementHelper;
import com.parabank.qa.helpers.WaitHelper;
import com.parabank.qa.config.RuntimeManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    private ElementHelper elementHelper;

    public RegisterPage() {
        PageFactory.initElements(RuntimeManager.getDriver(), this);
        elementHelper = new ElementHelper();
    }

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(id = "customer.firstName")
    private WebElement firstName;

    @FindBy(id = "customer.lastName")
    private WebElement lastName;

    @FindBy(id = "customer.address.street")
    private WebElement address;

    @FindBy(id = "customer.address.city")
    private WebElement city;

    @FindBy(id = "customer.address.state")
    private WebElement state;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id = "customer.ssn")
    private WebElement ssn;

    @FindBy(id = "customer.username")
    private WebElement username;

    @FindBy(id = "customer.password")
    private WebElement password;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//h1[contains(text(),'Welcome')]")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//p[contains(text(),'Your account was created successfully')]")
    private WebElement registrationSuccessMsg;
    
    @FindBy(css = "span.error")  // or use the correct locator for your app
    private WebElement errorMessage;
    
    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    public void navigateToRegisterPage() {
        elementHelper.click(registerLink);
    }

    
    public void fillRegistrationForm(String firstNameVal, String lastNameVal, String addressVal,
            String cityVal, String stateVal, String zipVal,
            String phoneVal, String ssnVal, String user, String pass) {
elementHelper.sendText(firstName, firstNameVal);
elementHelper.sendText(lastName, lastNameVal);
elementHelper.sendText(address, addressVal);
elementHelper.sendText(city, cityVal);
elementHelper.sendText(state, stateVal);
elementHelper.sendText(zipCode, zipVal);
elementHelper.sendText(phoneNumber, phoneVal);
elementHelper.sendText(ssn, ssnVal);
elementHelper.sendText(username, user);
elementHelper.sendText(password, pass);
elementHelper.sendText(confirmPassword, pass);
}

    public void submitRegistrationForm() {
        elementHelper.click(registerButton);
    }

    public boolean isRegistrationSuccessful() {
        return new WaitHelper().waitForElementVisible(registrationSuccessMsg).isDisplayed();
    }
    
    public void enterConfirmPassword(String confirmPass) {
        elementHelper.sendText(confirmPassword, confirmPass);
    }

    public boolean isErrorMessageDisplayed(String expectedMessage) {
        return new WaitHelper().waitForElementVisible(errorMessage).getText().contains(expectedMessage);
    }
    
    public void logout() {
        if (logoutLink.isDisplayed()) {
            elementHelper.click(logoutLink);
        }
    }
}

