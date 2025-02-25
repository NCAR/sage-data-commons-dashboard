package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.exception.DateParseException;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.model.ResourceList;
import edu.sage.datacommonsdashboard.repository.JobQueueRepository;
import edu.sage.datacommonsdashboard.util.DateToEpochConverter;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static edu.sage.datacommonsdashboard.util.DateToEpochConverter.convertDateStringToEpoch;


@Controller
public class DisplayJobsController {

    private final JobQueueRepository jobQueueRepository;
    private final TimeZoneUtil timeZoneUtil = new TimeZoneUtil();
    private final DateToEpochConverter dateToEpochConverter = new DateToEpochConverter();

    public DisplayJobsController(JobQueueRepository jobQueueRepository) {

        this.jobQueueRepository = jobQueueRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/table")
    public String showCasperJobsTable(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("formattedTimestamp", timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));
        model.addAttribute("pbsVersion", jobData.getPbsVersion());
        model.addAttribute("pbsServer", jobData.getPbsServer());
        model.addAttribute("timestamp", jobData.getTimestamp());

        // Add the list to the model
        model.addAttribute("jobs", getJobViewModels(jobData));

        return "job-data-table-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/table")
    public String showDerechoJobsTable(Model model)  {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("formattedTimestamp", timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));
        model.addAttribute("pbsVersion", jobData.getPbsVersion());
        model.addAttribute("pbsServer", jobData.getPbsServer());
        model.addAttribute("timestamp", jobData.getTimestamp());

        // Add the list to the model if parseable. Else, ignore
        try {
            model.addAttribute("jobs", getJobViewModels(jobData));
        } catch (RuntimeException e) {
            // Do nothing (don't update page)
        }

        return "job-data-table-view";  // The thymeleaf file
    }

    List<JobViewModel> getJobViewModels(JobData jobData) {

        List<JobViewModel> jobViewModels = new ArrayList<>();

        // Iterate through all jobs in JobData
        jobData.getJobs().forEach((jobId, job) -> {
            ResourceList resources = job.getResourceList();

            // Convert String date/times safely using a utility method

            Long ctimeEpoch = null;
            Long mtimeEpoch = null;
            Long qtimeEpoch = null;
            Long stimeEpoch = null;
            Long obittimeEpoch = null;
            Long etimeEpoch = null;
            try {
                ctimeEpoch = convertDateStringToEpoch(job.getCtime());
                mtimeEpoch = convertDateStringToEpoch(job.getMtime());
                qtimeEpoch = convertDateStringToEpoch(job.getEtime());
                stimeEpoch = convertDateStringToEpoch(job.getStime());
                obittimeEpoch = convertDateStringToEpoch(job.getObittime());
                etimeEpoch = convertDateStringToEpoch(job.getEtime());

            } catch (ParseException e) {
                throw new DateParseException(e);
            }

            // Create a JobViewModel for each job
            JobViewModel viewModel = new JobViewModel(
                    jobId,
                    job.getJobName(),
                    job.getJobOwner(),
                    job.getJobState(),
                    job.getQueue(),
                    job.getServer(),
                    job.getAccountName(),
                    job.getCheckpoint(),
                    ctimeEpoch,
                    job.getHoldTypes(),
                    job.getJoinPath(),
                    job.getKeepFiles(),
                    job.getMailPoints(),
                    mtimeEpoch,
                    job.getPriority(),
                    qtimeEpoch,
                    job.getRerunable(),
                    stimeEpoch,
                    obittimeEpoch,
                    job.getShellPathList(),
                    job.getJobdir(),
                    job.getSubstate(),
                    job.getComment(),
                    etimeEpoch,
                    job.getUmask(),
                    job.getRunCount(),
                    job.getEligibleTime(),
                    job.getExitStatus(),
                    job.getSubmitArguments(),
                    job.getProject(),
                    job.getSubmitHost(),
                    job.getServerInstanceId(),
                    resources.getMem(),
                    resources.getMpiprocs(),
                    resources.getMps(),
                    resources.getNcpus(),
                    resources.getNgpus(),
                    resources.getNodect(),
                    resources.getNvpus(),
                    resources.getPlace(),
                    resources.getSelect(),
                    resources.getWalltime()
            );

            jobViewModels.add(viewModel);
        });

        return jobViewModels;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/tablefull")
    public String showCasperJobsTableFull(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs Full");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/tablefull")
    public String showDerechoJobsFullTable(Model model) {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs Full");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp", timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));

        return "job-data-table-full-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs")
    public String showCasperJobs(Model model)  {

        JobData jobData = jobQueueRepository.getCasperQstatJobsJson();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp",  timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));

        return "job-data-view";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs")
    public String showDerechoJobsText(Model model) {

        JobData jobData = jobQueueRepository.getDerechoQstatJobsJson();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("jobData", jobData);
        model.addAttribute("formattedTimestamp",  timeZoneUtil.convertTimestampToDateString(jobData.getTimestamp(), ZoneId.of("America/Denver")));

        return "job-data-view";  // The thymeleaf file
    }

}
