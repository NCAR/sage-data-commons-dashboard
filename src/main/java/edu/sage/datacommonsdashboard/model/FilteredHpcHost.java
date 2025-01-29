package edu.sage.datacommonsdashboard.model;

public class FilteredHpcHost {

    // Just holds the hpcHost fields we want to display
    private final String hostname;
    private final String fqdn;
    private String username;
    private final HpcHost.Status status;

    public FilteredHpcHost(String hostname, String fqdn, String username, HpcHost.Status status) {

        this.hostname = hostname;
        this.fqdn = fqdn;
        this.username = username;
        this.status = status;
    }

    public String getHostname() {
        return hostname;
    }

    public String getFqdn() {
        return fqdn;
    }

    public HpcHost.Status getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

