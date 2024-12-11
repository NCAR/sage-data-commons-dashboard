package edu.sage.datacommonsdashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class DisplayJobsController {

    private JobRepository jobRepository;

    public DisplayJobsController(JobRepository jobRepository) {

        this.jobRepository = jobRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs")
    public String showCasperJobs(Model model) throws IOException {

        String jsonData = jobRepository.getCasperQstatJobsJson();
        JobData jobData = this.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/table")
    public String showCasperJobsTable(Model model) throws IOException {

        String jsonData = jobRepository.getCasperQstatJobsJson();
        JobData jobData = this.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/table")
    public String showDerechoJobsTable(Model model) throws IOException {

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        JobData jobData = this.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-view";  // The thymeleaf file
    }

//    @GetMapping(value = "/hpc/dashboard/casper/queue")
//    public String showCasperPageJson(Model model) throws IOException {
//
//        String jsonData = jobRepository.getCasperQstatQueueJson();
//
//        model.addAttribute("pageTitle", "Casper Qstat Queue");
//        model.addAttribute("jobData", jobData);
//
//        return "display-queue-data";  // The thymeleaf file
//    }

//    @GetMapping(value = "/hpc/dashboard/derecho/queue")
//    public String showDerechoQueueText(Model model) throws IOException {
//
//        String queueData = jobRepository.getDerechoQstatQueueJson();
//        QueueData jobData = this.convertJsonToQueueData(queueData);
//
//        model.addAttribute("pageTitle", "Derecho Qstat Queue");
//        model.addAttribute("jobData", jobData);
//
//        return "display-queue-data";  // The thymeleaf file
//    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs")
    public String showDerechoJobsText(Model model) throws IOException {

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        JobData jobData = this.convertJsonToMap(jsonData);

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

    private JobData convertJsonToJobData(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JobData jobData = objectMapper.readValue(jsonData, JobData.class);

        return jobData;
    }


    public JobData convertJsonToMap(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JobData jobData = objectMapper.readValue(jsonData, JobData.class);

        return jobData;
    }

}
