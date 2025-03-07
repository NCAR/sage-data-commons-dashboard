package edu.sage.datacommonsdashboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.exception.JsonParsingException;
import edu.sage.datacommonsdashboard.model.Job;
import edu.sage.datacommonsdashboard.model.JobData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobDataJsonConverterTest {

    private ObjectMapper objectMapper;
    private JobDataJsonConverter jobDataJsonConverter;

    @BeforeEach
    void setUp() {
        objectMapper = mock(ObjectMapper.class);
        jobDataJsonConverter = new JobDataJsonConverter();
    }

    @Test
    void convertJsonToJobData_successfulParsing() throws JsonProcessingException {

        String json = """
                {
                "timestamp": 1731533521,
                "pbs_version": "2022.1.5.2024",
                "pbs_server": "casper-pbs",
                "Jobs": {
                    "2399360.casper-pbs": {
                        "Job_Name": "jobname1",
                        "Job_Owner": "abc"
                        }
                     }
                 }""";

        Job job1 = new Job();
        job1.setJobName("jobname1");
        job1.setJobOwner("abc");

        JobData expectedObject = new JobData();

         expectedObject.setTimestamp(1731533521);
         expectedObject.setPbsServer("casper-pbs");
         expectedObject.setPbsVersion("2022.1.5.2024");
         expectedObject.setJobs(Map.of("2399360.casper-pbs", job1));

        when(objectMapper.readValue(json, JobData.class)).thenReturn(expectedObject); // Mock behavior

        JobData result = jobDataJsonConverter.convertJsonToJobData(json);

        assertNotNull(result);
        assertEquals("casper-pbs", result.getPbsServer());
        assertEquals("abc", result.getJobs().get("2399360.casper-pbs").getJobOwner());

        //verify(objectMapper, times(1)).readValue(json, JobData.class); // Verify ObjectMapper behavior
    }

    @Test
    void convertJsonToJobData_invalidJson_throwsJsonParsingException() throws JsonProcessingException {

        String invalidJson = """
                plain text not json
                """;

        when(objectMapper.readValue(invalidJson, JobData.class)).thenThrow(new JsonProcessingException("JSON parsing error") {});

        JsonParsingException exception = assertThrows(JsonParsingException.class, () -> {
            jobDataJsonConverter.convertJsonToJobData(invalidJson);
        });

        // Just read the first line of the error message because the rest is too long
        String fullMessage = exception.getMessage();
        String firstLine = fullMessage.split("\n")[0]; // Split by newline and get the first line

        String expectedMessage = "Failed to parse JSON at line: 1, column: 6. ==>Problematic line: plain text not json.";

        assertEquals(expectedMessage, firstLine);
    }

    @Test
    void convertJsonToJobData_emptyJson_throwsJsonParsingException() throws JsonProcessingException {

        String emptyJson = "";

        JsonParsingException exception = assertThrows(JsonParsingException.class, () -> {
            jobDataJsonConverter.convertJsonToJobData(emptyJson);
        });

        verify(objectMapper, times(0)).readValue(emptyJson, JobData.class); // Ensure ObjectMapper is not used
    }

    @Test
    void convertJsonToJobData_nullJson_throwsJsonParsingException() {

        String nullJson = null;

        JsonParsingException exception = assertThrows(JsonParsingException.class, () -> {
            jobDataJsonConverter.convertJsonToJobData(nullJson);
        });

    }
}
