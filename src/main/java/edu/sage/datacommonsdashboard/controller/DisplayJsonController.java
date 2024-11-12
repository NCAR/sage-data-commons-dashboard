package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.service.QueueDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private QueueDataService queueDataService;

    public DisplayJsonController(QueueDataService queueDataService) {
        this.queueDataService = queueDataService;
    }

    @GetMapping(value = "/jsonrow")
    public QueueData showJsonRow() {

        QueueData queueData = queueDataService.createQueueRow();

        return queueData;
    }

    @GetMapping(value = "/jsondata")
    public List<String> showJsonFile() {

       return queueDataService.convertTextToJson();
    }

}
