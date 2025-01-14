package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;

public class JSchHpcHostGatewayFactory implements HpcHostGatewayFactory {

    private final int port;
    private final String username;

    public JSchHpcHostGatewayFactory(int port, String username) {
        this.port = port;
        this.username = username;
    }

    @Override
    public HpcHostGateway create(HpcHost hpcHost) {
        return new JSchHpcHostGateway(createJSchSessionFactory(hpcHost));
    }

    private JSchSessionFactory createJSchSessionFactory(HpcHost hpcHost) {
        return new JSchSessionFactory(
                hpcHost.getFqdn(),
                port,
                username
        );
    }
}
