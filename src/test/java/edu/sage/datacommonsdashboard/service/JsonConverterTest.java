package edu.sage.datacommonsdashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.exception.JsonParsingException;
import edu.sage.datacommonsdashboard.model.Job;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.util.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonConverterTest {

    private ObjectMapper objectMapper;
    private JsonConverter jsonConverter;

    @BeforeEach
    void setUp() {
        objectMapper = mock(ObjectMapper.class);
        jsonConverter = new JsonConverter();
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

        JobData result = jsonConverter.convertJsonToJobData(json);

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
            jsonConverter.convertJsonToJobData(invalidJson);
        });

        assertEquals("Failed to parse JSON: " + invalidJson, exception.getMessage());
    }

    @Test
    void convertJsonToJobData_emptyJson_throwsJsonParsingException() throws JsonProcessingException {
        // Arrange
        String emptyJson = "";

        JsonParsingException exception = assertThrows(JsonParsingException.class, () -> {
            jsonConverter.convertJsonToJobData(emptyJson);
        });

        assertEquals("Input JSON is null or empty.", exception.getMessage());
        verify(objectMapper, times(0)).readValue(emptyJson, JobData.class); // Ensure ObjectMapper is not used
    }

    @Test
    void convertJsonToJobData_nullJson_throwsJsonParsingException() {

        String nullJson = null;

        JsonParsingException exception = assertThrows(JsonParsingException.class, () -> {
            jsonConverter.convertJsonToJobData(nullJson);
        });

        assertEquals("Input JSON is null or empty.", exception.getMessage());
    }
}
