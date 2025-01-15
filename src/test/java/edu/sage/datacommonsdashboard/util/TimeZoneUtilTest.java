package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeZoneUtilTest {

    TimeZoneUtil timeZoneUtil = new TimeZoneUtil();

    @BeforeEach
    void setUp() {

        timeZoneUtil = new TimeZoneUtil();
    }

    @Test
    void testConvertTimestamp_validTimestampToDateString() {

        Integer timestamp = 1672531200; // Example: Dec 31, 2022, 00:00:00 UTC
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = timeZoneUtil.convertTimestampToDateString(timestamp);

        assertEquals(expected, result, "The converted date should match the expected date");
    }

    @Test
    void testConvertTimestamp_zeroTimestampToDateString() {

        Integer timestamp = 0; // Epoch time: Jan 1, 1970, 00:00:00 UTC
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = timeZoneUtil.convertTimestampToDateString(timestamp);

        assertEquals(expected, result, "The converted date for zero timestamp should be the epoch date");
    }

    @Test
    void testConvertTimestamp_negativeTimestampToDateString() {

        Integer timestamp = -86400; // -1 day in seconds (Dec 31, 1969, 00:00:00 UTC)
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = timeZoneUtil.convertTimestampToDateString(timestamp);

        assertEquals(expected, result, "The converted date for a negative timestamp should match the expected date");
    }

    @Test
    void testConvertTimestamp_nullTimestampToDateString() {

        Integer timestamp = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            timeZoneUtil.convertTimestampToDateString(timestamp);
        });

        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"timestamp\" is null", exception.getMessage(), "Expected NullPointerException with the correct message");
    }

    @Test
    public void testGetNowInDenverTimeZoneAbbreviation() {

        // Get the expected abbreviation based on the current time in Denver
        ZonedDateTime nowInDenver = ZonedDateTime.now(ZoneId.of("America/Denver"));
        String expectedAbbreviation = nowInDenver.getZone().getRules().isDaylightSavings(nowInDenver.toInstant()) ? "MDT" : "MST";

        // Call the method to get the actual abbreviation
        String actualAbbreviation = timeZoneUtil.getAbbreviationNowInDenverTimeZone();

        // Assert that the abbreviation matches the expected value
        assertEquals(expectedAbbreviation, actualAbbreviation, "The time zone abbreviation should match Denver's current time zone.");
    }

    @Test
    public void testGetTimeZoneAbbreviationForSpecificDate() {

        // Define a specific date and time in Denver's time zone for testing
        ZonedDateTime specificDateInDenver = ZonedDateTime.of(2024, 11, 13, 12, 0, 0, 0,
                ZoneId.of("America/Denver"));

        // Determine the expected abbreviation based on whether it's daylight saving time
        String expectedAbbreviation = "MST";

        String actualAbbreviation = timeZoneUtil.getAbbreviationForZonedDateTime(specificDateInDenver);

        // Assert that the abbreviation matches the expected value
        assertEquals(expectedAbbreviation, actualAbbreviation, "The time zone abbreviation for the specific date should match Denver's current time zone.");
    }


    @Test
    public void testGetAbbreviationForZonedDateTime() {

        // Create a ZonedDateTime with a specific time zone and time
        ZonedDateTime dateTimeInNewYork = ZonedDateTime.of(2024, 7, 15, 12, 0, 0, 0, ZoneId.of("America/New_York"));

        // Call the method to get the time zone abbreviation
        String abbreviation = timeZoneUtil.getAbbreviationForZonedDateTime(dateTimeInNewYork);

        // Ensure the abbreviation is correct
        assertEquals("EDT", abbreviation, "Abbreviation should be 'EDT' for Eastern Daylight Time in July");

        // Additional check for a different time zone (e.g., London)
        ZonedDateTime dateTimeInLondon = ZonedDateTime.of(2024, 1, 15, 12, 0, 0, 0, ZoneId.of("Europe/London"));
        String londonAbbreviation = timeZoneUtil.getAbbreviationForZonedDateTime(dateTimeInLondon);

        assertEquals("GMT", londonAbbreviation, "Abbreviation should be 'GMT' for London in Standard Time (January)");
    }
}
