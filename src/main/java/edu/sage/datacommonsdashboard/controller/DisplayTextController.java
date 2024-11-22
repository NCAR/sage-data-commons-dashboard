package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class DisplayTextController {

    private FileRepository fileRepository;

    public DisplayTextController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper/jobs/text")
    public String showCasperPage(Model model) throws IOException {

        String textCasperOutput = fileRepository.getCasperQstatJobsText();

        model.addAttribute("pageTitle", "Casper Qstat Jobs");
        model.addAttribute("textOutput", textCasperOutput);

        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/queue/text")
    public String showCasperPageJson(Model model) throws IOException {

        String textCasperOutput = fileRepository.getCasperQstatQueueText();

        model.addAttribute("pageTitle", "Casper Qstat Queue");
        model.addAttribute("textOutput", textCasperOutput);

        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/queue/text")
    public String showDerechoQueueText(Model model) throws IOException {

        String textDerechoOutput = fileRepository.getDerechoQstatQueueText();

        model.addAttribute("pageTitle", "Derecho Qstat Queue");
        model.addAttribute("textOutput", textDerechoOutput);

        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/jobs/text")
    public String showDerechoJobsText(Model model) throws IOException {

        String textDerechoOutput = fileRepository.getDerechoQstatQueueText();

        model.addAttribute("pageTitle", "Derecho Qstat Jobs");
        model.addAttribute("textOutput", textDerechoOutput);

        return "display-queue-data";  // The thymeleaf file
    }

}
