package com.backbase.utilities;

import org.openqa.selenium.support.Color;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    /**
     * Simple method to transform a date object to a specific
     * date format.
     *
     * @param dateFormat   - The format of the date e.g yyyy-MM-dd
     * @param dateToFormat - The date to be formatted
     */
    public String convertDateToFormat(String dateFormat, Date dateToFormat) {
        SimpleDateFormat formatter = null;
        try {
            formatter = new SimpleDateFormat(dateFormat);
        } catch (IllegalArgumentException e) {
            System.out.println("Formatter not found for " + dateFormat + ", check the pattern used is supported by SimpleDateFormat");
        }
        return formatter.format(dateToFormat);
    }

    public String convertToDisplayDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = simpleDateFormat.parse(date);
        simpleDateFormat.applyPattern("dd MMM YYYY");
        return simpleDateFormat.format(dateObj);
    }

    public String rgbToHex(String rgbValue) {
        return Color.fromString(rgbValue).asHex();
    }
}