package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;

public interface HpcHostGatewayFactory {

    HpcHostGateway create(HpcHost hpcHost);
}
