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

            int errorLine = e.getLocation().getLineNr();
            int errorColumn = e.getLocation().getColumnNr();

            // Try to extract the problematic line from the JSON
            String problematicLine = extractJsonLine(json, errorLine);

            // TODO: logger
            System.out.println("Problematic Line: " + problematicLine);
            System.out.println(json); // Because the file gets replaced every minute so we'd have no other record

            // Extract location details from the exception
            String errorMessage = String.format(
                    "Failed to parse JSON at line: %d, column: %d. ==>Problematic line: %s.\nError message: %s. ",
                    errorLine,
                    errorColumn,
                    problematicLine,
                    e.getOriginalMessage()
            );

            throw new JsonParsingException(errorMessage, e);

        }  catch (Exception e) {

            throw new JsonParsingException("Failed to parse JSON.", e);
        }
    }

    private String extractJsonLine(String json, int errorLine) {

        String[] lines = json.split("\n");

        // Ensure the errorLine is within bounds
        if (errorLine > 0 && errorLine <= lines.length) {
            // Return the specific line causing the issue
            String targetLine = lines[errorLine - 1].trim(); // -1 because line numbers are 1-based
            return targetLine;
        }

        // Return a fallback message if the line cannot be found
        return "<Unable to extract problematic line>";
    }
}
