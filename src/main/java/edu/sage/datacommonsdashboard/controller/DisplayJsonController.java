package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.JobRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private JobRepository jobRepository;

    public DisplayJsonController(JobRepository jobRepository) {

        this.jobRepository = jobRepository;
    }

    @GetMapping("/hpc/dashboard/casper/jobs/json")
    public ResponseEntity<String> showCasperJobsJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String jsonData = jobRepository.getCasperQstatJobsJson();
        return ResponseEntity.ok().headers(headers).body(jsonData);

    }

    @GetMapping("/hpc/dashboard/casper/queue/json")
    public ResponseEntity<String> showCasperQueueJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String jsonData = jobRepository.getCasperQstatQueueJson();
        return ResponseEntity.ok().headers(headers).body(jsonData);
    }

    @GetMapping(value = "/hpc/dashboard/derecho/queue/json")
    public ResponseEntity<String> showDerechoQueueJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String jsonData = jobRepository.getDerechoQstatQueueJson();
        return ResponseEntity.ok().headers(headers).body(jsonData);
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    @GetMapping("/hpc/dashboard/derecho/jobs/json")
    public ResponseEntity<String> showDerechoJobsJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        return ResponseEntity.ok().headers(headers).body(jsonData);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unable to process the request: " + ex.getMessage());
    }

}
