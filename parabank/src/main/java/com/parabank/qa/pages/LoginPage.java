package com.parabank.qa.pages;

import com.parabank.qa.helpers.ElementHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.parabank.qa.config.RuntimeManager;

public class LoginPage {

    private ElementHelper elementHelper;

    public LoginPage() {
        PageFactory.initElements(RuntimeManager.getDriver(), this);
        elementHelper = new ElementHelper();
    }

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement loginButton;

    public void enterUsername(String username) {
        elementHelper.sendText(usernameInput, username);
    }

    public void enterPassword(String password) {
        elementHelper.sendText(passwordInput, password);
    }

    public void clickLogin() {
        elementHelper.click(loginButton);
    }

    public boolean isOnAccountOverviewPage() {
        return RuntimeManager.getDriver().getTitle().contains("ParaBank | Accounts Overview");
    }
}
