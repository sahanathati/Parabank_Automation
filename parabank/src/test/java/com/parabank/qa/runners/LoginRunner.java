package com.parabank.qa.runners;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/login.feature", 
        glue = {"com.parabank.qa.stepdefinitions", "com.parabank.qa.hooks"},
        plugin = {
                "pretty",
                "html:logs/cucumber-html-report.html",
                "json:logs/cucumber-report.json"
        },
        tags = "@feature",
        monochrome = true,
        dryRun = false
)

@Listeners(com.parabank.qa.listeners.ExtentReportListener.class)
public class LoginRunner extends AbstractTestNGCucumberTests {

}