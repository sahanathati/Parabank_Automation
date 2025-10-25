package com.parabank.qa.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    // Get current date in desired format
    public static String getCurrentDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    // Convert a given date string to another format
    public static String convertDateFormat(String dateStr, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat srcFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat destFormat = new SimpleDateFormat(toFormat);
            Date date = srcFormat.parse(dateStr);
            return destFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
