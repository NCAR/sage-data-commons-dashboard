package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.ServerStatusResponse;
import edu.sage.datacommonsdashboard.service.ServerStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerStatusController {

    private final ServerStatusService serverStatusService;

    public ServerStatusController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    // Load initial page with the list of servers and date
    @GetMapping(value = "/hpc/dashboard/host-ws")
    public String showServers(Model model) {

        model.addAttribute("pageTitle", "HPC Hosts WS");

        // Get server status from the service
        ServerStatusResponse serverStatusResponse = serverStatusService.getServerStatusResponse();

        model.addAttribute("serverStatus", serverStatusService.buildJson(serverStatusResponse));

        return "hpc-host-ws"; // html page
    }

}
