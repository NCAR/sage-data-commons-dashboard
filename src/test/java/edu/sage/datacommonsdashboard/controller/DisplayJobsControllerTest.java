package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayJobsControllerTest {

    private JobQueueRepository mockJobQueueRepository = mock(JobQueueRepository.class);

    private final DisplayJobsController controller = new DisplayJobsController(mockJobQueueRepository);

    private TimeZoneUtil timeZoneUtil = mock(TimeZoneUtil.class);

    @Test
    void testShowCasperJobsTable() {

        Model model = mock(Model.class);

        JobData mockJobData = mock(JobData.class);

        Integer timestamp = 1672531200;

        when (mockJobData.getTimestamp()).thenReturn(timestamp); // Dec 31, 2022

        // Mock the util to return a formatted timestamp
        when(timeZoneUtil.convertTimestampToDateString(1625481600)).thenReturn("2021-07-05 12:00:00 MDT");

        when(mockJobQueueRepository.getCasperQstatJobsJson()).thenReturn(mockJobData);

        String viewName = controller.showCasperJobsTable(model);

        // Assert
        assertEquals("job-data-table-view", viewName, "The method should return the correct Thymeleaf view name");
        verify(model, times(1)).addAttribute("pageTitle", "Casper Qstat Jobs");
        verify(model, times(1)).addAttribute("jobData", mockJobData);
      //  verify(model, times(1)).addAttribute("formattedTimestamp", result); // Verifying that the timestamp was converted and passed to the model

        // Verify repository and converter interactions
        verify(mockJobQueueRepository, times(1)).getCasperQstatJobsJson();
    }





}
