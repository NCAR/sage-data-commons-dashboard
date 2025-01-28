package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.gateway.hpchost.HpcHostGateway;
import edu.sage.datacommonsdashboard.gateway.hpchost.impl.JSchHpcHostGateway;
import edu.sage.datacommonsdashboard.gateway.hpchost.impl.JSchSessionFactory;
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
    public HpcHostGateway HpcHostGateway() {

        JSchSessionFactory sessionFactory = new JSchSessionFactory();

        return new JSchHpcHostGateway(sessionFactory);
    }

    @Bean
    public HpcHostTransformer hpcHostTransformer() {
        return new HpcHostTransformer();
    }
}
