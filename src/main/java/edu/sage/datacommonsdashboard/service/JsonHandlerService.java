package edu.sage.datacommonsdashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.JobData;
import org.springframework.stereotype.Service;

@Service
public class JsonHandlerService {

    public JobData convertJsonToJobData(String json) {

        if (json == null || json.isEmpty()) {
            throw new JsonParsingException("Input JSON is null or empty.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JobData jobData = objectMapper.readValue(json, JobData.class);

            return jobData;

        } catch (JsonProcessingException e) {

            throw new JsonParsingException("Failed to parse JSON: " + json, e);
        }
    }
}
