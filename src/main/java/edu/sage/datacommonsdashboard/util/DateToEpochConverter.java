package edu.sage.datacommonsdashboard.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateToEpochConverter {

    /**
     * Converts a date string (e.g. "Thu Sep 19 14:05:04 2024") to an epoch timestamp (in milliseconds).
     * The date is interpreted in the America/Denver time zone (Mountain Time with DST rules applied).
     *
     * @param dateStr The date string in the format "EEE MMM dd HH:mm:ss yyyy".
     * @return The epoch timestamp as a long.
     * @throws ParseException if the given date string cannot be parsed.
     **/
    public static long convertToEpoch(String dateStr) throws ParseException {

        // Validate that the input string is not null or empty
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new ParseException("Date string is null or empty", 0);
        }

        // Define the expected date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        dateFormat.setLenient(false);

        // Set the time zone to America/Denver (Mountain Time)
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Denver"));

        // Parse the date and convert it to epoch milliseconds
        long epochMillis = dateFormat.parse(dateStr).getTime();

        System.out.println("Input: " + dateStr + " -> Parsed Epoch (America/Denver): " + epochMillis); // Debug log
        return epochMillis;
    }

}