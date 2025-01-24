package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SshAccessibleTask {

    private final HpcHostRepository hpcHostRepository;
    private final HpcHostGatewayRepository hpcHostGatewayRepository;

    public SshAccessibleTask(HpcHostRepository hpcHostRepository, HpcHostGatewayRepository hpcHostGatewayRepository) {
        this.hpcHostRepository = hpcHostRepository;
        this.hpcHostGatewayRepository = hpcHostGatewayRepository;
    }

//    @Scheduled(fixedDelayString = "${scheduler.fixedDelay:60000}")
    public void execute() {
        hpcHostRepository.getAll().forEach(this::setAvailabilityStatus);
    }

    private void setAvailabilityStatus(HpcHost hpcHost) {
        hpcHost.setStatus(isHpcHostSshAccessible(hpcHost) ? HpcHost.Status.ONLINE : HpcHost.Status.OFFLINE);
    }

    private Boolean isHpcHostSshAccessible(HpcHost hpcHost) {
        HpcHostGateway hpcHostGateway = hpcHostGatewayRepository.get(hpcHost);
        return hpcHostGateway.isSshAccessible();
    }
}
