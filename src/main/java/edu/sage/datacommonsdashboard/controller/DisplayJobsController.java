package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobRepository;
import edu.sage.datacommonsdashboard.service.JsonHandlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayJobsController {

    private JobRepository jobRepository;
    private JsonHandlerService jsonHandlerService;

    public DisplayJobsController(JobRepository jobRepository, JsonHandlerService jsonHandlerService) {

        this.jobRepository = jobRepository;
        this.jsonHandlerService = jsonHandlerService;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs")
    public String showCasperJobs(Model model)  {

        String jsonData = jobRepository.getCasperQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/table")
    public String showCasperJobsTable(Model model)  {

        String jsonData = jobRepository.getCasperQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/table")
    public String showDerechoJobsTable(Model model)  {

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/tablefull")
    public String showCasperJobsTableFull(Model model)  {

        String jsonData = jobRepository.getCasperQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/tablefull")
    public String showDerechoJobsFullTable(Model model) {

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs")
    public String showDerechoJobsText(Model model) {

        String jsonData = jobRepository.getDerechoQstatJobsJson();
        JobData jobData = jsonHandlerService.convertJsonToJobData(jsonData);

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

}
