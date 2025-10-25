package com.parabank.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
            prop = new Properties();
            prop.load(fis);
            System.out.println("✅ Loaded properties file.");
            System.out.println("Browser: " + prop.getProperty("browser")); // Sanity check
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }
}
