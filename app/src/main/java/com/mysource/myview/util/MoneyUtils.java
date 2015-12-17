package com.mysource.myview.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtils {

    public static String convertMoneyString(float value, int maxFraction) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMaximumFractionDigits(maxFraction);
        return format.format(value).replaceAll("[$]", "");//$22,850.00
    }

    public static String formatReadableValue(double value, int maxFraction) {
        return formatReadableValue(value, maxFraction, 1);
    }

    public static String formatReadableValue(double value, int maxFraction, int minInteger) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMinimumFractionDigits(0);
        format.setMinimumIntegerDigits(minInteger);
        format.setMaximumFractionDigits(maxFraction);
        return format.format(value);
    }

    public static String convertMoneyString(double value, int maxFraction) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMaximumFractionDigits(maxFraction);
        return format.format(value).replaceAll("[$]", "");//$22,850.00
    }

    public static String convertMoneyString(String value, int maxFraction) {
        try {
            double doubleValue = Double.parseDouble(value);
            return convertMoneyString(doubleValue,maxFraction);
        } catch (Exception e) {
            return value;
        }
    }

    public static String formatCurrencyValue(double value) {
        long iValue = (long) value;
        double fraction = value - iValue;

        int numFraction = 0;
        if (fraction != 0) {
            double maxValue = 0.1f;
            numFraction = 1;

            while (fraction < maxValue) {
                numFraction++;
                maxValue *= 0.1;
            }

            if (numFraction >= 1)
                numFraction++;
        }

        return formatReadableValue(iValue, 0) + (fraction != 0 ? formatReadableValue(fraction, numFraction, 0) : "");
    }

}
