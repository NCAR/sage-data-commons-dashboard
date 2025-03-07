package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.ServerStatusResponse;
import edu.sage.datacommonsdashboard.service.ServerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerStatusScheduler {

    private final SimpMessagingTemplate messagingTemplate;
    private final ServerStatusService serverStatusService;

    @Autowired
    public ServerStatusScheduler(SimpMessagingTemplate messagingTemplate, ServerStatusService serverStatusService) {
        this.messagingTemplate = messagingTemplate;
        this.serverStatusService = serverStatusService;
    }

    // Update every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void updateServerStatus() {

        ServerStatusResponse serverStatusResponse = serverStatusService.getServerStatusResponse();

        // Send updated server status to all subscribers
        messagingTemplate.convertAndSend("/topic/status", serverStatusService
                .buildJson(serverStatusResponse));
    }
}