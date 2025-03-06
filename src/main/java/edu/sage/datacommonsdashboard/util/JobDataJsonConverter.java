package edu.sage.datacommonsdashboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.exception.JsonParsingException;
import edu.sage.datacommonsdashboard.model.JobData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobDataJsonConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JobDataJsonConverter.class);

    public JobData convertJsonToJobData(String json) {

        JobData jobData;

        try {

            jobData = objectMapper.readValue(json, JobData.class);

        } catch (JsonProcessingException e) {

            int errorLine = e.getLocation().getLineNr();
            int errorColumn = e.getLocation().getColumnNr();

            // Try to extract the problematic line from the JSON
            String problematicLine = extractJsonLine(json, errorLine);

            logger.error("Problematic Line Number " + errorLine + ": " + problematicLine);
            logger.error("JSON Input:\n{}", json);

            // Extract location details from the exception
            String errorMessage = String.format(
                    "Failed to parse JSON at line: %d, column: %d. ==>Problematic line: %s.\nError message: %s. ",
                    errorLine,
                    errorColumn,
                    problematicLine,
                    e.getOriginalMessage()
            );

            throw new JsonParsingException(errorMessage, e);

        } catch (Exception e) {

            throw new JsonParsingException("Failed to parse JSON.", e);
        }

        return jobData;
    }

    private String extractJsonLine(String json, int errorLine) {

        String targetLine;

        String[] lines = json.split("\n");

        // Ensure the errorLine is within bounds
        if (errorLine > 0 && errorLine <= lines.length) {
            // Return the specific line causing the issue
            targetLine = lines[errorLine - 1].trim(); // -1 because line numbers are 1-based
        } else {
            // Return a fallback message if the line cannot be found
            targetLine = "<Unable to extract problematic line>";
        }

        return targetLine;
    }
}
