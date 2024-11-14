package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.service.FileRepository;
import edu.sage.datacommonsdashboard.service.QueueDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


// For returning JSON directly (bypass view)
@RestController
public class DisplayFileController {

    private QueueDataRepository queueDataRepository;
    private FileRepository fileRepository;

    public DisplayFileController(QueueDataRepository queueDataRepository, FileRepository fileRepository) {

        this.queueDataRepository = queueDataRepository;
        this.fileRepository = fileRepository;
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

    // Change mapping to /hpc/dashboard/casper/file/{filename}
    // to get file name from url when it lives in resources
    @GetMapping("/readfile.json")
    public String readFile() {
        try {
            return fileRepository.readFileFromResources("qstat_casper_queue.json");
        } catch (IOException e) {

            e.printStackTrace();
            return "Error occurred while reading file.";
        }
    }

}
