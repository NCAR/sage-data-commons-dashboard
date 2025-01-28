package edu.sage.datacommonsdashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.ServerStatus;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

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
        // model.addAttribute("hpcHosts", getHpcHosts());

        ServerStatus serverStatus = new ServerStatus();

        serverStatus.setHosts(repository.getAll());
        serverStatus.setTimestamp( Math.toIntExact(Instant.now().getEpochSecond()));

        model.addAttribute("serverStatus", buildJson(serverStatus));

        return "hpc-host-ws"; // html page
    }

    // Update every 10 seconds
    @Scheduled(fixedRate = 10000)
   // @SendTo("/topic/status")
    public void updateServerStatus() {

        ServerStatus serverStatus = new ServerStatus();

        serverStatus.setHosts(repository.getAll());
        serverStatus.setTimestamp( Math.toIntExact(Instant.now().getEpochSecond()));

        // Send updated server status to all subscribers
        messagingTemplate.convertAndSend("/topic/server-status", buildJson(serverStatus));
    }

    // Convert object to json
    String buildJson(ServerStatus serverStatus) {

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonOutput = null;

        try {
            jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serverStatus);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Print the JSON string
        System.out.println(jsonOutput);

        return jsonOutput;
    }

}
