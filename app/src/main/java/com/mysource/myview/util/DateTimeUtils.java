package com.mysource.myview.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public static final String SERVER_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"; //2013-04-24T22:53:03+00:00

    public static final String LAST_UPDATE_DATE_TIME_PATTERN = "HH:mm, EEEE - MM/dd/yyyy"; // 14:24, Today - 09/21/2015

    public static final String SIMPLE_DATE_TIME_PATTERN = "MMM dd, yyyy - hh:mm aa"; // Oct 19, 2015 - 01:00 AM

    public static final String SIMPLE_DATE_PATTERN = "MM-dd-yyyy";

    public static final String DATE_MONTH_FULL_PATTERN = "dd MMMM yyyy";

    public static final String MONTH_DAY_YEAR_PATTERN = "MM/dd/yyyy";

    public static final String MMM_DD_YYYY_PATTERN = "MMM dd, yyyy";

    public static final String FULL_DATE_PATTERN = "EEEE - MM/dd/yyyy";

    public static String convertServerTime(String dateTime, String convertPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(SERVER_DATE_TIME_PATTERN, java.util.Locale.getDefault());
        try {
            Date date = sdf.parse(dateTime);
            return convertServerTime(date, convertPattern);
        } catch (ParseException e) {
            return "";
        }
    }

    public static String convertServerTime(Date dateTime, String convertPattern) {
        SimpleDateFormat sdf2 = new SimpleDateFormat(convertPattern, java.util.Locale.getDefault());
        return sdf2.format(dateTime);
    }

    public static Date getDateFromString(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(SERVER_DATE_TIME_PATTERN, java.util.Locale.getDefault());
        try {
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getCurrentTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat(SERVER_DATE_TIME_PATTERN, java.util.Locale.getDefault());
        Date date = new Date();
        return sdf.format(date);
    }
}
