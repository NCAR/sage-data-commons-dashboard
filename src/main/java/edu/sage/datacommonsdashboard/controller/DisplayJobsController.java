package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class DisplayJobsController {

    private JobQueueRepository jobQueueRepository;

    public DisplayJobsController(JobQueueRepository jobQueueRepository) {

        this.jobQueueRepository = jobQueueRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/table")
    public String showCasperJobsTable(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", this.convertTimestamp(jobData.getTimestamp()));

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/table")
    public String showDerechoJobsTable(Model model)  {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", this.convertTimestamp(jobData.getTimestamp()));

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/tablefull")
    public String showCasperJobsTableFull(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", this.convertTimestamp(jobData.getTimestamp()));

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/tablefull")
    public String showDerechoJobsFullTable(Model model) {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", this.convertTimestamp(jobData.getTimestamp()));

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs")
    public String showCasperJobs(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs")
    public String showDerechoJobsText(Model model) {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);

        return "job-data-view";  // The thymeleaf file
    }

    protected Date convertTimestamp (Integer timestamp) {
        return new Date (timestamp * 1000L);
    }

}
