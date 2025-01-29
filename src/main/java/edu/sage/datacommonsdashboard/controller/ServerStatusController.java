package edu.sage.datacommonsdashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.FilteredHpcHost;
import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.model.ServerStatusResponse;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ServerStatusController {

    private final HpcHostRepository repository;
    private final TimeZoneUtil timeZoneUtil = new TimeZoneUtil();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public ServerStatusController(HpcHostRepository repository) {
        this.repository = repository;
    }

    // Load initial page with the list of servers and date
    @GetMapping(value = "/hpc/dashboard/host-ws")
    public String showServers(Model model) {

        model.addAttribute("pageTitle", "HPC Hosts WS");

        ServerStatusResponse serverStatusResponse = getServerStatusResponse();

        model.addAttribute("serverStatus", buildJson(serverStatusResponse));

        return "hpc-host-ws"; // html page
    }

    // Update every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void updateServerStatus() {

        ServerStatusResponse serverStatusResponse = getServerStatusResponse();

        // Send updated server status to all subscribers
        messagingTemplate.convertAndSend("/topic/status", buildJson(serverStatusResponse));
    }

    // Method to return filtered list of hosts
    public List<FilteredHpcHost> getFilteredHosts(List<HpcHost> hosts) {

        // Transform the original host list to filtered list
        return hosts.stream()
                .map(host -> new FilteredHpcHost(
                        host.getHostname(),
                        host.getFqdn(),
                        host.getUsername(),
                        host.getStatus()
                ))
                .collect(Collectors.toList());
    }

    private ServerStatusResponse getServerStatusResponse() {

        Long timestamp = Instant.now().getEpochSecond();
        List<HpcHost> hosts = repository.getAll();

        ServerStatusResponse serverStatusResponse = new ServerStatusResponse(timestamp, this.getFilteredHosts(hosts) );
        return serverStatusResponse;
    }

    // Convert object to json
    String buildJson(ServerStatusResponse serverStatusResponse) {

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonOutput = null;

        try {
            jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serverStatusResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Print the JSON string
       // System.out.println(jsonOutput);

        return jsonOutput;
    }

}
