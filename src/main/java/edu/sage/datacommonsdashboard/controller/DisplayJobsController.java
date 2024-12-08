package edu.sage.datacommonsdashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class DisplayJobsController {

    private FileRepository fileRepository;

    public DisplayJobsController(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs")
    public String showCasperJobs(Model model) throws IOException {

        String jsonData = fileRepository.getCasperQstatJobsJson();
        JobData jobData = this.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

//    @GetMapping(value = "/hpc/dashboard/casper/queue")
//    public String showCasperPageJson(Model model) throws IOException {
//
//        String jsonData = fileRepository.getCasperQstatQueueJson();
//
//        model.addAttribute("pageTitle", "Casper Qstat Queue");
//        model.addAttribute("jobData", jobData);
//
//        return "display-queue-data";  // The thymeleaf file
//    }

//    @GetMapping(value = "/hpc/dashboard/derecho/queue")
//    public String showDerechoQueueText(Model model) throws IOException {
//
//        String queueData = fileRepository.getDerechoQstatQueueJson();
//        QueueData jobData = this.convertJsonToQueueData(queueData);
//
//        model.addAttribute("pageTitle", "Derecho Qstat Queue");
//        model.addAttribute("jobData", jobData);
//
//        return "display-queue-data";  // The thymeleaf file
//    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs")
    public String showDerechoJobsText(Model model) throws IOException {

        String jsonData = fileRepository.getDerechoQstatJobsJson();
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

    // This won't work cuz the json doesn't have an array
    private List<JobData> convertJsonToJobDataList(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Use TypeReference to indicate a list of JobData
        List<JobData> jobDataList = objectMapper.readValue(jsonData, new TypeReference<List<JobData>>() {});

        return jobDataList;
    }

    public JobData convertJsonToMap(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Use TypeReference for dynamic JSON keys
       // Map<String, JobData> resultMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, JobData>>() {});

        JobData jobData = objectMapper.readValue(jsonData, JobData.class);

        return jobData;
    }

}
