package edu.sage.datacommonsdashboard.model;

import java.util.UUID;

public class HpcHost {

    private UUID id;
    private String hostname;
    private String fqdn;
    private String username;
    private String expectedPrompt;
    private Status status = Status.OFFLINE;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExpectedPrompt() {
        return expectedPrompt;
    }

    public void setExpectedPrompt(String expectedPrompt) {
        this.expectedPrompt = expectedPrompt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        HpcHost hpcHost = (HpcHost) o;
        return id.equals(hpcHost.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
