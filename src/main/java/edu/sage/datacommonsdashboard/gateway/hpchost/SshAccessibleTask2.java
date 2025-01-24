package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SshAccessibleTask2 {

    private final HpcHostRepository hpcHostRepository;
    private final HpcHostGateway hpcHostGateway;

    public SshAccessibleTask2(HpcHostRepository hpcHostRepository,
                              HpcHostGateway hpcHostGateway) {

        this.hpcHostRepository = hpcHostRepository;
        this.hpcHostGateway = hpcHostGateway;
    }

    @Scheduled(fixedDelayString = "${scheduler.fixedDelay:60000}")
    public void execute() {

        this.hpcHostRepository.getAll()
                .forEach(host -> host.setStatus(getStatus(host)));
    }

    private HpcHost.Status getStatus(HpcHost host) {

        HpcHost.Status status = HpcHost.Status.OFFLINE;

        // TokenResponse:
        SshAvailableDetails details = SshAvailableDetails.of(b -> b.setHostname(host.getFqdn())
                .setUsername(host.getUsername())
                .setHostKey(host.getHostKey())
                .setExpectedPrompt(host.getExpectedPrompt()));

        if (this.hpcHostGateway.isSshAccessible(details)) {

            status = HpcHost.Status.ONLINE;
        }

        return status;
    }
}
