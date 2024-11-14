package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.service.QueueDataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayTextController {

    private QueueDataRepository queueDataRepository;

    public DisplayTextController(QueueDataRepository queueDataRepository) {
        this.queueDataRepository = queueDataRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper")
    public String showCasperPage(Model model) {

        String textCasperOutput = queueDataRepository.getCasperQstatDataText();

        model.addAttribute("pageTitle", "Casper Qstat Text Output");

        model.addAttribute("textOutput", textCasperOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho")
    public String showDerechoPage(Model model) {

        String textDerechoOutput = queueDataRepository.getDerechoQstatDataText();

        model.addAttribute("pageTitle", "Derecho Qstat Text Output");

        model.addAttribute("textOutput", textDerechoOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/json")
    public String showCasperPageJson(Model model) {

        String textCasperOutput = queueDataRepository.getCasperQstatDataJson();

        model.addAttribute("pageTitle", "Casper Qstat Json Output");

        model.addAttribute("textOutput", textCasperOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/json")
    public String showDerechoPageJson(Model model) {

        String textDerechoOutput = queueDataRepository.getDerechoQstatDataJson();

        model.addAttribute("pageTitle", "Derecho Qstat Json Output");

        model.addAttribute("textOutput", textDerechoOutput);
        return "display-queue-data";  // The thymeleaf file
    }


}
