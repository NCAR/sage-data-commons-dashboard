package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DisplayJsonControllerTest {

    @MockBean
    private FileRepository mockFileRepository;

    @Test
    public void when_request_casper_jobs_json__then_get_json() throws Exception {

        String mockJsonData = "{\"job\":\"sample\"}";
        when(mockFileRepository.getCasperQstatJobsJson()).thenReturn(mockJsonData);

        DisplayJsonController controller = new DisplayJsonController(mockFileRepository);

        ResponseEntity<String> response = controller.showCasperJobsJson();
        assertEquals(mockJsonData, response.getBody());
    }

}
