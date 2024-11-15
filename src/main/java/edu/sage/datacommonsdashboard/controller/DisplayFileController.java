package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.repository.FileRepositoryImpl;
import edu.sage.datacommonsdashboard.repository.QueueDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


// For returning JSON directly (bypass view)
@RestController
public class DisplayFileController {

    private QueueDataRepository queueDataRepository;
    private FileRepositoryImpl fileRepositoryImpl;

    public DisplayFileController(QueueDataRepository queueDataRepository, FileRepositoryImpl fileRepositoryImpl) {

        this.queueDataRepository = queueDataRepository;
        this.fileRepositoryImpl = fileRepositoryImpl;
    }

    @GetMapping(value = "/jsonrow")
    public QueueData showJsonRow() {

        QueueData queueData = queueDataRepository.createQueueRow();

        return queueData;
    }

    @GetMapping(value = "/jsondata")
    public List<String> showJsonFile() {

       return queueDataRepository.convertTextToJson();
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
    // http://localhost:8080/hpc/dashboard/file?filename=qstat_casper_queue.json
    @GetMapping("/hpc/dashboard/file")
    public String readSystemFile(@RequestParam String filename) {
        try {
            return fileRepositoryImpl.readFileWithPath(filename);
        } catch (IOException e) {

            e.printStackTrace();
            return "Error occurred while reading file.";
        }
    }

}
