package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;

import java.util.*;

public class HpcHostGatewayRepository {

    private final HpcHostGatewayFactory hpcHostGatewayFactory;
    private final Map<UUID, HpcHostGateway> gateways;

    public HpcHostGatewayRepository(HpcHostGatewayFactory hpcHostGatewayFactory) {
        this.hpcHostGatewayFactory = hpcHostGatewayFactory;
        this.gateways = new HashMap<>();
    }

    public HpcHostGateway get(HpcHost hpcHost) {
        if (!gateways.containsKey(hpcHost.getId())) {
            gateways.put(hpcHost.getId(), hpcHostGatewayFactory.create(hpcHost));
        }
        return gateways.get(hpcHost.getId());
    }
}
