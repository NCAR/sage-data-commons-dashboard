package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DisplayTextControllerTest {

    @MockBean
    private JobRepository mockJobRepository;

    @MockBean
    private Model mockModel;

    @Test
    public void when_request_casper_jobs_text__then_get_view_name() throws Exception {

        when(mockJobRepository.getCasperQstatJobsText()).thenReturn("mockTextOutput");

        DisplayTextController controller = new DisplayTextController(mockJobRepository);

        String viewName = controller.showCasperPage(mockModel);
        assertEquals("display-queue-data", viewName);
    }

}
