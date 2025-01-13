package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.repository.HpcHostRepositoryImpl;
import edu.sage.datacommonsdashboard.service.HpcHostService;
import edu.sage.datacommonsdashboard.service.HpcHostServiceImpl;
import edu.sage.datacommonsdashboard.service.HpcHostTransformer;
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
    public HpcHostService hpcHostService() {
        return new HpcHostServiceImpl(hpcHostRepository(), new HpcHostTransformer());
    }
}
