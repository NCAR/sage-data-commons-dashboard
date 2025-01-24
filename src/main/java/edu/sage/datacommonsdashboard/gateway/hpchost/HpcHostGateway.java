package edu.sage.datacommonsdashboard.gateway.hpchost;

public interface HpcHostGateway {

    Boolean isSshAccessible();

    boolean isSshAccessible(SshAvailableDetails details);
}
