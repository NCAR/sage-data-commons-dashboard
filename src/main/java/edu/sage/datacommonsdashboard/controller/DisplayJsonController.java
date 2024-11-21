package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.FileRepositoryImpl;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private FileRepositoryImpl fileRepositoryImpl;

    public DisplayJsonController(FileRepositoryImpl fileRepositoryImpl) {

        this.fileRepositoryImpl = fileRepositoryImpl;
    }

    // to get file name from url when it lives in resources
   // http://localhost:8080/hpc/dashboard?filename=qstat_casper_queue.json
    @GetMapping("/hpc/dashboard")
    public String readResourceFile(@RequestParam String filename) throws IOException {
        try {
            return fileRepositoryImpl.readFileFromResources(filename);
        } catch (IOException e) {

            e.printStackTrace();

            throw new IOException("Error reading file: " + e.getMessage());
        }
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    // http://localhost:8080/hpc/dashboard/file?filename=casper_qstat_queue.json
    // http://localhost:8080/hpc/dashboard/file?filename=casper_qstat_jobs.json
    @GetMapping("/hpc/dashboard/file")
    public ResponseEntity<String> readSystemFile(@RequestParam String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {

            String jsonData = fileRepositoryImpl.readFileWithPath(filename);

            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

//            return new ResponseEntity<>("An error occurred while processing the request.",
//                    HttpStatus.INTERNAL_SERVER_ERROR);

            // This message is displayed on the 500 global error page
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }

}
