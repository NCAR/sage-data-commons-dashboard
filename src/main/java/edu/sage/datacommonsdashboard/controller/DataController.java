package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.QueueDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// For returning JSON directly (bypass view)
@RestController
public class DataController {

    private QueueDataService queueDataService;

    public DataController(QueueDataService queueDataService) {
        this.queueDataService = queueDataService;
    }

    @GetMapping(value = "/data")
    public String showData() {

        return queueDataService.getCasperQstatDataText();

    }
}
