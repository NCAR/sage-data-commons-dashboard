package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.gateway.hpchost.HpcHostGatewayFactory;
import edu.sage.datacommonsdashboard.gateway.hpchost.HpcHostGatewayRepository;
import edu.sage.datacommonsdashboard.gateway.hpchost.JSchHpcHostGatewayFactory;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.repository.HpcHostRepositoryImpl;
import edu.sage.datacommonsdashboard.controller.HpcHostTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DashboardConfiguration {

    private final HpcConfiguration hpcConfiguration;

    public DashboardConfiguration(HpcConfiguration hpcConfiguration) {
        this.hpcConfiguration = hpcConfiguration;
    }

    @Bean
    public HpcHostRepository hpcHostRepository() {
        return new HpcHostRepositoryImpl(hpcConfiguration.getHosts());
    }

    @Bean
    public HpcHostGatewayFactory hpcHostGatewayFactory() {
        return new JSchHpcHostGatewayFactory(
                hpcConfiguration.getSsh().getPort(),
                hpcConfiguration.getSsh().getUsername()
        );
    }

    @Bean
    public HpcHostGatewayRepository hpcHostGatewayRepository() {
        return new HpcHostGatewayRepository(hpcHostGatewayFactory());
    }

    @Bean
    public HpcHostTransformer hpcHostTransformer() {
        return new HpcHostTransformer();
    }
}
