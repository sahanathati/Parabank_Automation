package com.parabank.qa.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.parabank.qa.config.Constants;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHelper {

    private static boolean root = false;
    private static String currentRunPath;

    public static Logger getLogger(Class<?> cls) {
        Logger logger = Logger.getLogger(cls);
        if (!root) {
            createLogsDirectory();
            PropertyConfigurator.configure("src/test/resources/config/log4j.properties");
            root = true;
        }
        return logger;
    }

    private static void createLogsDirectory() {
        currentRunPath = Constants.LOGS_FOLDER + "/run_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        System.setProperty("current.log.path", currentRunPath);  // Used in log4j.properties
        new File(currentRunPath).mkdirs();
    }

    // ✅ THIS IS THE METHOD YOU NEED
    public static String getCurrentRunPath() {
        return currentRunPath;
    }
}
