package edu.sage.datacommonsdashboard.model;

import java.util.List;

// The structure for the Json
public class ServerStatusResponse {

    private Long timestamp;
    private List<FilteredHpcHost> hosts;

    public ServerStatusResponse(Long timestamp, List<FilteredHpcHost> hosts) {
        this.timestamp = timestamp;
        this.hosts = hosts;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<FilteredHpcHost> getHosts() {
        return hosts;
    }
}
