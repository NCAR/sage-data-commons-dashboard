package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HpcHostController {

    private final HpcHostRepository repository;
    private final HpcHostTransformer transformer;

    public HpcHostController(HpcHostRepository repository, HpcHostTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @GetMapping(value = "/hpc/dashboard/host")
    public String showHpcHosts(Model model) {
        model.addAttribute("pageTitle", "HPC Hosts");
        model.addAttribute("hpcHosts", getHpcHosts());
        return "hpc-host";
    }

    List<HpcHostModel> getHpcHosts() {
        return repository.getAll().stream()
                .map(transformer::transform)
                .collect(Collectors.toList());
    }
}
