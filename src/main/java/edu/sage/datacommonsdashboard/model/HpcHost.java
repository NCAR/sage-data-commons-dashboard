package edu.sage.datacommonsdashboard.model;

import java.util.UUID;

public class HpcHost {

    private UUID id;
    private String hostname;
    private String fqdn;
    private Status status = Status.ONLINE;

    public enum Status {
        ONLINE,
        OFFLINE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
