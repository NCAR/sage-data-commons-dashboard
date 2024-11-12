package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.service.QueueDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private QueueDataService queueDataService;

    public DisplayJsonController(QueueDataService queueDataService) {
        this.queueDataService = queueDataService;
    }

    @GetMapping(value = "/jsondata")
    public QueueData showData() {

        QueueData queueData = queueDataService.createQueueRow();

        return queueData;
    }

}
