package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.repository.FileRepository;
import edu.sage.datacommonsdashboard.repository.FileRepositoryImpl;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private FileRepository fileRepository;

    public DisplayJsonController(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    @GetMapping("/hpc/dashboard/casper/jobs/json")
    public ResponseEntity<String> showCasperJobsJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String jsonData = fileRepository.getCasperQstatJobsJson();
            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    @GetMapping("/hpc/dashboard/casper/queue/json")
    public ResponseEntity<String> showCasperQueueJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String jsonData = fileRepository.getCasperQstatJobsJson();
            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

//            return new ResponseEntity<>("An error occurred while processing the request.",
//                    HttpStatus.INTERNAL_SERVER_ERROR);

            // This message is displayed on the 500 global error page
            throw new IOException(e.getMessage());
        }
    }

    @GetMapping(value = "/hpc/dashboard/derecho/queue/json")
    public ResponseEntity<String> showDerechoQueueJson(Model model) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String jsonData = fileRepository.getDerechoQstatQueueJson();
            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

            // This message is displayed on the 500 global error page
            throw new IOException(e.getMessage());
        }
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    @GetMapping("/hpc/dashboard/derecho/jobs/json")
    public ResponseEntity<String> showDerechoJobsJson() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String jsonData = fileRepository.getDerechoQstatJobsJson();
            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

            // This message is displayed on the 500 global error page
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    @GetMapping("/hpc/dashboard/file")
    public ResponseEntity<String> readSystemFile(@RequestParam String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String jsonData = fileRepository.getCasperQstatJobsJson();
            return ResponseEntity.ok().headers(headers).body(jsonData);

        } catch (IOException e) {

            e.printStackTrace();

            // This message is displayed on the 500 global error page
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/jsonrow")
    public QueueData showJsonRow() {

        QueueData queueData = fileRepository.createQueueRow();

        return queueData;
    }

    @GetMapping(value = "/jsondata")
    public List<String> showJsonFile() {

        return fileRepository.convertTextToJson();
    }

}
