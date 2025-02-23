package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateToEpochConverterTest {

    @Test
    @DisplayName("Test valid date conversion to epoch")
    void testValidDateConversion() throws ParseException {

        String validDate = "Thu Sep 19 14:05:04 2024"; // Known valid date and time

        long epochMillis = DateToEpochConverter.convertToEpoch(validDate);

        assertEquals(1726776304000L, epochMillis, "Epoch value did not match expected."); // Replace with actual epoch time
    }

    @Test
    @DisplayName("Test valid date in DST conversion to epoch")
    void testDSTValidDateConversion() throws ParseException {

        String validDate = "Wed July 5 06:00:00 2023"; // Known valid date and time

        long epochMillis = DateToEpochConverter.convertToEpoch(validDate);

        assertEquals(1688558400000L, epochMillis, "Epoch value did not match expected."); // Replace with actual epoch time
    }

    @Test
    @DisplayName("Test null date string")
    void testNullDate() {

        String nullDate = null;

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertToEpoch(nullDate),
                "Expected exception for null input date.");
    }

    @Test
    @DisplayName("Test empty date string")
    void testEmptyString() {

        String emptyDate = "";

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertToEpoch(emptyDate),
                "Expected exception for empty date string.");
    }

    @Test
    @DisplayName("Test date in invalid format")
    void testInvalidFormat() {

        String invalidDate = "2024-09-19";

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertToEpoch(invalidDate),
                "Expected exception for invalid date format.");
    }

    @Test
    @DisplayName("Test date out of range throws ParseException")
    void testDateOutOfRange() {

        String outOfRangeDate = "Thu Sep 19 24:05:04 2024"; // Invalid hour

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertToEpoch(outOfRangeDate),
                "Expected exception for out of range date.");
    }

    @Test
    @DisplayName("Test invalid day of the week")
    void testInvalidDayOfWeek() {

        String invalidDayOfWeek = "Fri Sep 19 14:05:04 2024"; // Sept 19, 2024 is actually a Thursday

        assertThrows(ParseException.class,
                () -> DateToEpochConverter.convertToEpoch(invalidDayOfWeek),
                "Expected a ParseException due to the invalid day of the week.");

    }
}