package edu.sage.datacommonsdashboard.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.json.JSONException;
import org.json.JSONObject;


public class TextFileToJson {

    public static void main(String[] args) {

        String textFilePath = "/Users/cgrant/derecho_trim.txt";
        String jsonFilePath = "/Users/cgrant/derecho_trim.json";

        try {
            List<String> lines = readLinesFromTextFile(textFilePath);
            ArrayNode jsonArray = convertLinesToJson(lines);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFilePath), jsonArray);

            System.out.println("JSON file created successfully!");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readLinesFromTextFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static ArrayNode convertLinesToJson(List<String> lines) throws JSONException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = objectMapper.createArrayNode();

        for (String line : lines) {
            // Assuming each line represents a simple JSON object
            //ObjectNode jsonNode = objectMapper.readValue(line, ObjectNode.class);
            JSONObject jsonObject = convertToJson(line);
            jsonArray.add(String.valueOf(jsonObject));
        }

        return jsonArray;
    }

    private static JSONObject convertToJson(String spaceSeparatedString) throws JSONException {

        JSONObject jsonObject = new JSONObject();

       // String[] elements = spaceSeparatedString.split(" ");

        // Temporary
        jsonObject.put("key", spaceSeparatedString);

        return jsonObject;
    }




}
