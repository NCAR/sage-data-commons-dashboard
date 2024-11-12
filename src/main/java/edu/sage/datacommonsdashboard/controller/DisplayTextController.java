package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.QueueDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayTextController {

    private QueueDataService queueDataService;

    public DisplayTextController(QueueDataService queueDataService) {
        this.queueDataService = queueDataService;
    }

    @GetMapping(value = "/hpc/dashboard/casper")
    public String showCasperPage(Model model) {

        String textCasperOutput = queueDataService.getCasperQstatDataText();

        model.addAttribute("pageTitle", "Casper Qstat Text Output");

        model.addAttribute("textOutput", textCasperOutput);
        return "displayStatusText";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho")
    public String showDerechoPage(Model model) {

        String textDerechoOutput = queueDataService.getDerechoQstatData();

        model.addAttribute("pageTitle", "Derecho Qstat Text Output");

        model.addAttribute("textOutput", textDerechoOutput);
        return "displayStatusText";  // The thymeleaf file
    }
}
