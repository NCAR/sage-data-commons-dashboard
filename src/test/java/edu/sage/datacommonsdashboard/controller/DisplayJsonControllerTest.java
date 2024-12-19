package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DisplayJsonControllerTest {

    @MockBean
    private JobQueueRepository mockJobQueueRepository;

    @Test
    public void when_request_casper_jobs_json__then_get_json() throws Exception {

        JobData mockJobData = mock(JobData.class);
        when(mockJobQueueRepository.getCasperQstatJobsJson()).thenReturn(mockJobData);

        DisplayJsonController controller = new DisplayJsonController(mockJobQueueRepository);

        ResponseEntity<JobData> response = controller.showCasperJobsJson();
        assertEquals(mockJobData, response.getBody());
    }

}
