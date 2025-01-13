package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.service.HpcHostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HpcHostController {

    private final HpcHostService hpcHostService;

    public HpcHostController(HpcHostService hpcHostService) {
        this.hpcHostService = hpcHostService;
    }

    @GetMapping(value = "/hpc/dashboard/host")
    public String showHpcHosts(Model model) {
        model.addAttribute("pageTitle", "HPC Hosts");
        model.addAttribute("hpcHosts", hpcHostService.getHpcHosts());
        return "hpc-host";
    }
}
