package edu.sage.datacommonsdashboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.exception.JsonParsingException;
import edu.sage.datacommonsdashboard.model.JobData;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

    public JobData convertJsonToJobData(String json) {

        if (json == null || json.isEmpty()) {
            throw new JsonParsingException("Input JSON is null or empty.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JobData jobData = objectMapper.readValue(json, JobData.class);

            return jobData;

        } catch (JsonProcessingException e) {

            // Extract location details from the exception
            String errorMessage = String.format(
                    "Failed to parse JSON at line: %d, column: %d. Error message: %s",
                    e.getLocation().getLineNr(),
                    e.getLocation().getColumnNr(),
                    e.getOriginalMessage()
            );

            throw new JsonParsingException(errorMessage, e);

        }  catch (Exception e) {

            throw new JsonParsingException("Failed to parse JSON", e);
    }
    }
}
