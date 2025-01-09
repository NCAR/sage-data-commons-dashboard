package edu.sage.datacommonsdashboard;

import edu.sage.datacommonsdashboard.model.HpcHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "hpc")
public class HpcConfiguration {

    private List<HpcHost> hosts;

    public HpcConfiguration() {
        this.hosts = new ArrayList<>();
    }

    public List<HpcHost> getHosts() {
        return hosts;
    }

    public void setHosts(List<HpcHost> hosts) {
        this.hosts = hosts;
    }
}
