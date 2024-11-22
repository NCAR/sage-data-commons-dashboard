package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.QueueData;
import edu.sage.datacommonsdashboard.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DisplayTextController {

    private FileRepository fileRepository;

    public DisplayTextController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping(value = "/hpc/dashboard/casper")
    public String showCasperPage(Model model) {

        String textCasperOutput = fileRepository.getCasperQstatDataText();

        model.addAttribute("pageTitle", "Casper Qstat Text Output");

        model.addAttribute("textOutput", textCasperOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho")
    public String showDerechoPage(Model model) {

        String textDerechoOutput = fileRepository.getDerechoQstatDataText();

        model.addAttribute("pageTitle", "Derecho Qstat Text Output");

        model.addAttribute("textOutput", textDerechoOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/casper/json")
    public String showCasperPageJson(Model model) {

        String textCasperOutput = fileRepository.getCasperQstatDataJson();

        model.addAttribute("pageTitle", "Casper Qstat Json Output");

        model.addAttribute("textOutput", textCasperOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/hpc/dashboard/derecho/json")
    public String showDerechoPageJson(Model model) {

        String textDerechoOutput = fileRepository.getDerechoQstatDataJson();

        model.addAttribute("pageTitle", "Derecho Qstat Json Output");

        model.addAttribute("textOutput", textDerechoOutput);
        return "display-queue-data";  // The thymeleaf file
    }

    @GetMapping(value = "/jsonrow")
    public QueueData showJsonRow() {

        QueueData queueData = fileRepository.createQueueRow();

        return queueData;
    }

    @GetMapping(value = "/jsondata")
    public List<String> showJsonFile() {

        return fileRepository.convertTextToJson();
    }

}
