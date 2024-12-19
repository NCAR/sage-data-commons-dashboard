package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// For returning JSON directly (bypass view)
@RestController
public class DisplayJsonController {

    private JobQueueRepository jobQueueRepository;

    public DisplayJsonController(JobQueueRepository jobQueueRepository) {

        this.jobQueueRepository = jobQueueRepository;
    }

    @GetMapping("/hpc/dashboard/casper/jobs/json")
    public ResponseEntity<JobData> showCasperJobsJson()  {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();
        return ResponseEntity.ok().headers(headers).body(jobData);
    }

    @GetMapping("/hpc/dashboard/casper/queue/json")
    public ResponseEntity<JobData> showCasperQueueJson()  {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        JobData jobData = jobQueueRepository.getCasperQstatQueueJson();
        return ResponseEntity.ok().headers(headers).body(jobData);
    }

    @GetMapping(value = "/hpc/dashboard/derecho/queue/json")
    public ResponseEntity<JobData> showDerechoQueueJson()  {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        JobData jobData = jobQueueRepository.getDerechoQstatQueueJson();
        return ResponseEntity.ok().headers(headers).body(jobData);
    }

    // to get file name from url when it lives on the file system
    // in a place designated by a property
    @GetMapping("/hpc/dashboard/derecho/jobs/json")
    public ResponseEntity<JobData> showDerechoJobsJson() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();
        return ResponseEntity.ok().headers(headers).body(jobData);
    }

}
