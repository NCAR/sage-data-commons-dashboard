package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.repository.HpcHostRepositoryImpl;
import edu.sage.datacommonsdashboard.query.HpcHostQuery;
import edu.sage.datacommonsdashboard.query.HpcHostQueryImpl;
import edu.sage.datacommonsdashboard.query.HpcHostTransformer;
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
    public HpcHostQuery hpcHostQuery() {
        return new HpcHostQueryImpl(hpcHostRepository(), new HpcHostTransformer());
    }
}
