package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateToEpochConverterTest {

    @Test
    @DisplayName("Test valid date conversion to epoch")
    void testValidDateConversion() throws ParseException {

        String validDate = "Thu Sep 19 14:05:04 2024"; // Known valid date and time

        long epochMillis = DateToEpochConverter.convertDateStringToEpoch(validDate);

        assertEquals(1726776304000L, epochMillis, "Epoch value did not match expected."); // Replace with actual epoch time
    }

    @Test
    @DisplayName("Test valid date in DST conversion to epoch")
    void testDSTValidDateConversion() throws ParseException {

        String validDate = "Wed July 5 06:00:00 2023"; // Known valid date and time

        long epochMillis = DateToEpochConverter.convertDateStringToEpoch(validDate);

        assertEquals(1688558400000L, epochMillis, "Epoch value did not match expected."); // Replace with actual epoch time
    }

    @Test
    @DisplayName("Test null date string")
    void testNullDate() throws ParseException {

        String nullDate = null;
        Long result = DateToEpochConverter.convertDateStringToEpoch(nullDate);
        assertNull(result, "Expected null to be returned for null input date.");

    }

    @Test
    @DisplayName("Test empty date string")
    void testEmptyString() throws ParseException {

        String emptyDate = "";
        Long result = DateToEpochConverter.convertDateStringToEpoch(emptyDate);
        assertNull(result, "Expected null to be returned for null input date.");

    }

    @Test
    @DisplayName("Test date in invalid format")
    void testInvalidFormat() {

        String invalidDate = "2024-09-19";

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertDateStringToEpoch(invalidDate),
                "Expected exception for invalid date format.");
    }

    @Test
    @DisplayName("Test date out of range throws ParseException")
    void testDateOutOfRange() {

        String outOfRangeDate = "Thu Sep 19 24:05:04 2024"; // Invalid hour

        assertThrows(ParseException.class, () -> DateToEpochConverter.convertDateStringToEpoch(outOfRangeDate),
                "Expected exception for out of range date.");
    }

    @Test
    @DisplayName("Test invalid day of the week")
    void testInvalidDayOfWeek() {

        String invalidDayOfWeek = "Fri Sep 19 14:05:04 2024"; // Sept 19, 2024 is actually a Thursday

        assertThrows(ParseException.class,
                () -> DateToEpochConverter.convertDateStringToEpoch(invalidDayOfWeek),
                "Expected a ParseException due to the invalid day of the week.");

    }

    // Epoch to Date conversion

    @Test
    void testConvertEpochToDateStringValidEpoch() {
        // Arrange
        Long epochMillis = 1700000000000L; // Example epoch (Nov 14, 2023, 03:33:20 UTC)
        SimpleDateFormat expectedFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

        // Act
        String result = DateToEpochConverter.convertEpochToDateString(epochMillis);

        // Assert
        Date date = new Date(epochMillis);
        assertEquals(expectedFormat.format(date), result, "The formatted date string should match the expected value.");
    }

    @Test
    void testConvertEpochToDateStringNullValue() {
        Long epochMillis = null;

        String result = DateToEpochConverter.convertEpochToDateString(epochMillis);

        assertNull(result, "For null epochMillis, the method should return null.");
    }

    @Test
    void testConvertEpochToDateStringEpochZero() {
        // Arrange
        Long epochMillis = 0L; // Epoch start date
        SimpleDateFormat expectedFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

        // Act
        String result = DateToEpochConverter.convertEpochToDateString(epochMillis);

        // Assert
        Date date = new Date(epochMillis);
        assertEquals(expectedFormat.format(date), result, "For epoch 0, the formatted date string should match the expected value.");
    }

    @Test
    void testConvertEpochToDateStringNegativeEpoch() {
        // Arrange
        Long epochMillis = -1L; // Negative epoch (Before UNIX epoch, Dec 31, 1969)
        SimpleDateFormat expectedFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

        // Act
        String result = DateToEpochConverter.convertEpochToDateString(epochMillis);

        // Assert
        Date date = new Date(epochMillis);
        assertEquals(expectedFormat.format(date), result, "For negative epoch, the formatted date string should match the expected value.");
    }

    @Test
    void testConvertEpochToDateStringFutureEpoch() {
        // Arrange
        Long epochMillis = 4102444800000L; // Jan 1, 2100 UTC
        SimpleDateFormat expectedFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

        // Act
        String result = DateToEpochConverter.convertEpochToDateString(epochMillis);

        // Assert
        Date date = new Date(epochMillis);
        assertEquals(expectedFormat.format(date), result, "The formatted date string for future epoch should match the expected value.");
    }


}