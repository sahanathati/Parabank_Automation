package com.parabank.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.parabank.qa.utils.LogHelper;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        String reportPath = LogHelper.getCurrentRunPath() + "/extent-report.html";
        new File(LogHelper.getCurrentRunPath()).mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Parabank Automation Report");
        spark.config().setReportName("Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
}
