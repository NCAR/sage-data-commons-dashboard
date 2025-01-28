package edu.sage.datacommonsdashboard.gateway.hpchost;

public interface HpcHostGateway {

    boolean isSshAccessible(SshAvailableDetails details);
}
