package edu.sage.datacommonsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HpcHostController {

    private final HpcHostQuery hpcHostQuery;

    public HpcHostController(HpcHostQuery hpcHostQuery) {
        this.hpcHostQuery = hpcHostQuery;
    }

    @GetMapping(value = "/hpc/dashboard/host")
    public String showHpcHosts(Model model) {
        model.addAttribute("pageTitle", "HPC Hosts");
        model.addAttribute("hpcHosts", hpcHostQuery.query());
        return "hpc-host";
    }
}
