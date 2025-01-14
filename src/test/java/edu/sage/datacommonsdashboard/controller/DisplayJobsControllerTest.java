package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DisplayJobsControllerTest {

    private JobQueueRepository mockJobQueueRepository = mock(JobQueueRepository.class);

    private final DisplayJobsController controller = new DisplayJobsController(mockJobQueueRepository);

    @Test
    void testConvertTimestamp_validTimestamp() {

        Integer timestamp = 1672531200; // Example: Dec 31, 2022, 00:00:00 UTC
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = controller.convertTimestamp(timestamp);

        assertEquals(expected, result, "The converted date should match the expected date");
    }

    @Test
    void testConvertTimestamp_zeroTimestamp() {

        Integer timestamp = 0; // Epoch time: Jan 1, 1970, 00:00:00 UTC
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = controller.convertTimestamp(timestamp);

        assertEquals(expected, result, "The converted date for zero timestamp should be the epoch date");
    }

    @Test
    void testConvertTimestamp_negativeTimestamp() {

        Integer timestamp = -86400; // -1 day in seconds (Dec 31, 1969, 00:00:00 UTC)
        long millisTimestamp = timestamp * 1000L;

        // Convert to Mountain Time
        ZonedDateTime expectedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(millisTimestamp), ZoneId.of("America/Denver"));

        // Format the expected result
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(expectedDateTime);

        String result = controller.convertTimestamp(timestamp);

        assertEquals(expected, result, "The converted date for a negative timestamp should match the expected date");
    }

    @Test
    void testConvertTimestamp_nullTimestamp() {

        Integer timestamp = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.convertTimestamp(timestamp);
        });

        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"timestamp\" is null", exception.getMessage(), "Expected NullPointerException with the correct message");
    }

    @Test
    void testShowCasperJobsTable() {

        Model model = mock(Model.class);

        JobData mockJobData = mock(JobData.class);

        Integer timestamp = 1672531200;

        when (mockJobData.getTimestamp()).thenReturn(timestamp); // Dec 31, 2022

        when(mockJobQueueRepository.getCasperQstatJobsJson()).thenReturn(mockJobData);

        String viewName = controller.showCasperJobsTable(model);

        String result = controller.convertTimestamp(timestamp);

        // Assert
        assertEquals("job-data-table-view", viewName, "The method should return the correct Thymeleaf view name");
        verify(model, times(1)).addAttribute("pageTitle", "Casper Qstat Jobs");
        verify(model, times(1)).addAttribute("jobData", mockJobData);
        verify(model, times(1)).addAttribute("formattedTimestamp", result); // Verifying that the timestamp was converted and passed to the model

        // Verify repository and converter interactions
        verify(mockJobQueueRepository, times(1)).getCasperQstatJobsJson();
    }
}
