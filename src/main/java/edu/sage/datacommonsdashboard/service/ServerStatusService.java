package edu.sage.datacommonsdashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.FilteredHpcHost;
import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.model.ServerStatusResponse;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServerStatusService {

    private final HpcHostRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ServerStatusService(HpcHostRepository repository) {
        this.repository = repository;
    }

    // Fetch filtered server status response
    public ServerStatusResponse getServerStatusResponse() {
        Long timestamp = Instant.now().getEpochSecond();
        List<HpcHost> hosts = repository.getAll();
        return new ServerStatusResponse(timestamp, getFilteredHosts(hosts));
    }

    // Filter the list of HPC hosts
    public List<FilteredHpcHost> getFilteredHosts(List<HpcHost> hosts) {
        return hosts.stream()
                .map(host -> new FilteredHpcHost(
                        host.getHostname(),
                        host.getFqdn(),
                        host.getUsername(),
                        host.getStatus()
                ))
                .collect(Collectors.toList());
    }

    // Convert ServerStatusResponse to JSON
    public String buildJson(ServerStatusResponse serverStatusResponse) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serverStatusResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}