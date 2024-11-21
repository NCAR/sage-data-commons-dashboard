package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.FileRepositoryImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


// For returning JSON directly (bypass view)
@RestController
public class DisplayFileController {

    private FileRepositoryImpl fileRepositoryImpl;

    public DisplayFileController(FileRepositoryImpl fileRepositoryImpl) {

        this.fileRepositoryImpl = fileRepositoryImpl;
    }

    // to get file name from url when it lives in resources
   // http://localhost:8080/hpc/dashboard?filename=qstat_casper_queue.json
    @GetMapping("/hpc/dashboard")
    public String readResourceFile(@RequestParam String filename) {
        try {
            return fileRepositoryImpl.readFileFromResources(filename);
        } catch (IOException e) {

            e.printStackTrace();
            return "Error occurred while reading file.";
        }
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    // http://localhost:8080/hpc/dashboard/file?filename=casper_qstat_queue.json
    // http://localhost:8080/hpc/dashboard/file?filename=casper_qstat_jobs.json
    @GetMapping("/hpc/dashboard/file")
    public ResponseEntity<String> readSystemFile(@RequestParam String filename) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {

            String jsonData = fileRepositoryImpl.readFileWithPath(filename);

            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

            return new ResponseEntity<>("An error occurred while processing the request.",
                        HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
