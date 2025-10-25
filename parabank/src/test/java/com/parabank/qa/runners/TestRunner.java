package com.parabank.qa.runners;

//import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/transfer-funds.feature", // points to feature file folder
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
public class TestRunner extends AbstractTestNGCucumberTests {

//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}

