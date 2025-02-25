package edu.sage.datacommonsdashboard.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateToEpochConverter {

    /**
     * Converts a date string (e.g. "Thu Sep 19 14:05:04 2024") to an epoch timestamp (in milliseconds).
     * The date is interpreted in the America/Denver time zone (Mountain Time with DST rules applied).
     *
     * @param dateStr The date string in the format "EEE MMM dd HH:mm:ss yyyy".
     * @return The epoch timestamp as a long.
     * @throws ParseException if the given date string cannot be parsed.
     **/
    public static Long convertDateStringToEpoch(String dateStr) throws ParseException {

        // Validate that the input string is not null or empty
        if (dateStr == null || dateStr.trim().isEmpty()) {
            //throw new ParseException("Date string is null or empty", 0);
            return null;
        }

        // Define the expected date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        dateFormat.setLenient(false);

        // Set the time zone to America/Denver (Mountain Time)
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Denver"));

        // Parse the date and convert it to epoch milliseconds
        long epochMillis = dateFormat.parse(dateStr).getTime();

       // System.out.println("Input: " + dateStr + " -> Parsed Epoch (America/Denver): " + epochMillis); // Debug log
        return epochMillis;
    }

    /**
     * Converts a Long epoch time in milliseconds to a formatted Date string.
     *
     * @param epochMillis The epoch time in milliseconds.
     * @return A formatted date string in the format "EEE MMM dd HH:mm:ss yyyy".
     */
    public static String convertEpochToDateString(Long epochMillis) {

        if (epochMillis == null) {
            return null; // Handle null input gracefully
        }

        // Create a SimpleDateFormat with the desired format (TBD - this is how the orig string looked, which is bad for sorting)
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

        // Convert the epoch time to a Date object
        Date date = new Date(epochMillis);

        // Format the Date object as a String
        return dateFormat.format(date);
    }
}
