package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DisplayTextControllerTest {

    @MockBean
    private JobQueueRepository mockJobQueueRepository;

    @MockBean
    private Model mockModel;

    @Test
    public void when_request_casper_jobs_text__then_get_view_name() throws Exception {

        when(mockJobQueueRepository.getCasperQstatJobsText()).thenReturn("mockTextOutput");

        DisplayTextController controller = new DisplayTextController(mockJobQueueRepository);

        String viewName = controller.showCasperPage(mockModel);
        assertEquals("display-queue-data", viewName);
    }

}
