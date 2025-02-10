package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.Job;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.model.ResourceList;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayJobsControllerTest {

    private JobQueueRepository mockJobQueueRepository = mock(JobQueueRepository.class);
    private TimeZoneUtil mockTimeZoneUtil = mock(TimeZoneUtil.class);
    private Model mockModel = mock(Model.class);

    private JobData fakeJobData;

    private  DisplayJobsController controller;

    Integer timestamp = 1672531200;

    @BeforeEach
    void setUp() {

        controller = new DisplayJobsController(mockJobQueueRepository);

        // Fake JobData setup
        fakeJobData = new JobData();
        fakeJobData.setTimestamp(timestamp);
        fakeJobData.setPbsVersion("19.1.3");
        fakeJobData.setPbsServer("pbs-derecho-server");

        // Fake jobs map
        Map<String, Job> fakeJobs = new HashMap<>();
        Job fakeJob = new Job();
        fakeJob.setJobName("Test Job");

        // Fake Resources for Job
        ResourceList fakeResources = new ResourceList();
        fakeResources.setMem("10gb");
        fakeResources.setNcpus(4);
        fakeResources.setWalltime("02:00:00");

        fakeJob.setResourceList(fakeResources);

        fakeJobs.put("123", fakeJob);
        fakeJobData.setJobs(fakeJobs);
    }

    @Test
    void testShowDerechoJobsTable() {

        // Arrange: mock JobData returned by the repository
        when(mockJobQueueRepository.getDerechoQstatJobsJson()).thenReturn(fakeJobData);

        // Mock TimeZoneUtil to format the timestamp
        when(mockTimeZoneUtil.convertTimestampToDateString(fakeJobData.getTimestamp(), ZoneId.of("America/Denver")))
                .thenReturn("2023-01-01 12:00 PM");

        String viewName = controller.showDerechoJobsTable(mockModel);

        // Verify view name
        assertEquals("job-data-table-view", viewName, "The method should return the correct Thymeleaf view name");

        // Verify model attributes
        verify(mockModel).addAttribute("pageTitle", "Derecho Qstat Jobs");
//        verify(mockModel).addAttribute("formattedTimestamp", "2023-10-01 12:00 PM");
        verify(mockModel).addAttribute("pbsVersion", "19.1.3");
        verify(mockModel).addAttribute("pbsServer", "pbs-derecho-server");
        verify(mockModel).addAttribute("timestamp", timestamp);

        // Verify the 'jobs' attribute is added from the processed map
        verify(mockModel).addAttribute(eq("jobs"), anyList());

        // Verify repository interaction
        verify(mockJobQueueRepository, times(1)).getDerechoQstatJobsJson();
    }

}
