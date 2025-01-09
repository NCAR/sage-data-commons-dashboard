package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.model.HpcHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DashboardConfiguration {

    private final HpcConfiguration hpcConfiguration;

    public DashboardConfiguration(HpcConfiguration hpcConfiguration) {
        this.hpcConfiguration = hpcConfiguration;
    }

    @Bean
    public List<HpcHost> hpcHosts() {
        return hpcConfiguration.getHosts();
    }
}
