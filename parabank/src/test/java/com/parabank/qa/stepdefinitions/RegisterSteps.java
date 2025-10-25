package com.parabank.qa.stepdefinitions;

import com.parabank.qa.helpers.AssertionHelper;
import com.parabank.qa.pages.RegisterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.Map;

public class RegisterSteps {

    private RegisterPage registerPage;

    // Page object is injected by the Hooks before each scenario
    public RegisterSteps() {
        this.registerPage = new RegisterPage();
    }

    @Given("user is on the registration page")
    public void user_is_on_registration_page() {
        registerPage.navigateToRegisterPage();
    }

    @When("user fills the registration form with:")
    public void user_fills_registration_form_with(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        registerPage.fillRegistrationForm(
                data.get("firstName"),
                data.get("lastName"),
                data.get("address"),
                data.get("city"),
                data.get("state"),
                data.get("zipCode"),
                data.get("phone"),
                data.get("ssn"),
                data.get("username"),
                data.get("password")
        );
    }

    @When("submits the registration form")
    public void submits_the_registration_form() {
        registerPage.submitRegistrationForm();
    }

    @Then("user should see a confirmation message {string}")
    public void user_should_see_confirmation_message(String expectedMsg) {
        AssertionHelper.verifyTrue(registerPage.isRegistrationSuccessful(), "Registration successful");
        registerPage.logout();  // ✅ log out after verification
    }

   
}
