package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import edu.sage.datacommonsdashboard.util.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DisplayJobsControllerTest {

    private JobQueueRepository mockJobQueueRepository = mock(JobQueueRepository.class);
    private JsonConverter mockJsonConverter= mock(JsonConverter.class);

    private final DisplayJobsController controller = new DisplayJobsController(mockJobQueueRepository, mockJsonConverter);

    @Test
    void testConvertTimestamp_validTimestamp() {

        Integer timestampInSeconds = 1672531200; // Example: Dec 31, 2022, 00:00:00 UTC
        Date expectedDate = new Date((long) timestampInSeconds * 1000); // Expected date
        Date actualDate = controller.convertTimestamp(timestampInSeconds);

        assertEquals(expectedDate, actualDate, "The converted date should match the expected date");
    }

    @Test
    void testConvertTimestamp_zeroTimestamp() {

        Integer timestampInSeconds = 0; // Epoch time: Jan 1, 1970, 00:00:00 UTC
        Date expectedDate = new Date(0); // Expected epoch date
        Date actualDate = controller.convertTimestamp(timestampInSeconds);

        assertEquals(expectedDate, actualDate, "The converted date for zero timestamp should be the epoch date");
    }

    @Test
    void testConvertTimestamp_negativeTimestamp() {

        Integer timestampInSeconds = -86400; // -1 day in seconds (Dec 31, 1969, 00:00:00 UTC)
        Date expectedDate = new Date((long) timestampInSeconds * 1000); // Expected date
        Date actualDate = controller.convertTimestamp(timestampInSeconds);

        assertEquals(expectedDate, actualDate, "The converted date for a negative timestamp should match the expected date");
    }

    @Test
    void testConvertTimestamp_nullTimestamp() {

        Integer timestampInSeconds = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.convertTimestamp(timestampInSeconds);
        });

        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"timestamp\" is null", exception.getMessage(), "Expected NullPointerException with the correct message");
    }

    @Test
    void testShowCasperJobsTable() {

        Model model = mock(Model.class);
        String mockJsonData = "{\"mock\":\"data\"}";
        JobData mockJobData = mock(JobData.class);

        when (mockJobData.getTimestamp()).thenReturn(1672531200); // Dec 31, 2022

        when(mockJobQueueRepository.getCasperQstatJobsJson()).thenReturn(mockJsonData);
        when(mockJsonConverter.convertJsonToJobData(mockJsonData)).thenReturn(mockJobData);

        String viewName = controller.showCasperJobsTable(model);

        // Assert
        assertEquals("job-data-table-view", viewName, "The method should return the correct Thymeleaf view name");
        verify(model, times(1)).addAttribute("pageTitle", "Casper Qstat Jobs");
        verify(model, times(1)).addAttribute("jobData", mockJobData);

        // Verifying that the timestamp was converted and passed to the model
        Date expectedDate = new Date(mockJobData.getTimestamp() * 1000L);
        verify(model, times(1)).addAttribute("formattedTimestamp", expectedDate);

        // Verify repository and converter interactions
        verify(mockJobQueueRepository, times(1)).getCasperQstatJobsJson();
        verify(mockJsonConverter, times(1)).convertJsonToJobData(mockJsonData);
    }
}
