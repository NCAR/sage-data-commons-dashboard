package edu.sage.datacommonsdashboard.gateway.hpchost.impl;

import edu.sage.datacommonsdashboard.gateway.hpchost.SshAvailableDetails;

public class SshAvailableDetailsImpl implements SshAvailableDetails {

    private final String hostname;
    private final String username;
    private final int port;
    private final String hostKey;
    private final String expectedPrompt;

    public SshAvailableDetailsImpl(String hostname,
                                   String username,
                                   int port,
                                   String hostKey,
                                   String expectedPrompt) {

        this.hostname = hostname;
        this.username = username;
        this.port = port;
        this.hostKey = hostKey;
        this.expectedPrompt = expectedPrompt;
    }

    @Override
    public String getHostname() {

        return this.hostname;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    public int getPort() {

        return this.port;
    }

    @Override
    public String getHostKey() {

        return this.hostKey;
    }

    @Override
    public String getExpectedPrompt() {

        return this.expectedPrompt;
    }
}
