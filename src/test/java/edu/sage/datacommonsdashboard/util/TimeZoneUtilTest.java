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

        String result = timeZoneUtil.convertTimestampToFormattedDate(timestamp, ZoneId.of("America/Denver"));

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

        String result = timeZoneUtil.convertTimestampToFormattedDate(timestamp, ZoneId.of("America/Denver"));

        assertEquals(expected, result, "The converted date for zero timestamp should be the epoch date");
    }

    @Test
    void testConvertTimestamp_negativeTimestampToDateString() {

        Integer timestamp = -86400; // -1 day in seconds (Dec 31, 1969, 00:00:00 UTC)
        long millisTimestamp = timestamp * 1000L;
        ZoneId testZoneId = ZoneId.of("America/Denver"); // Explicitly use ZoneId for Mountain Time

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp),testZoneId);

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        // This uses the current server zone, which may not be Denver (as with github actions)
        String result = timeZoneUtil.convertTimestampToFormattedDate(timestamp, ZoneId.of("America/Denver"));

        assertEquals(expected, result, "The converted date for a negative timestamp should match the expected date");
    }

    @Test
    void testConvertTimestamp_nullTimestampToDateString() {

        Integer timestamp = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            timeZoneUtil.convertTimestampToFormattedDate(timestamp, ZoneId.of("America/Denver"));
        });

        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"timestampSeconds\" is null", exception.getMessage(), "Expected NullPointerException with the correct message");
    }

}
