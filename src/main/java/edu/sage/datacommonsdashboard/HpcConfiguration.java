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
    private Ssh ssh;

    public HpcConfiguration() {
        this.hosts = new ArrayList<>();
        this.ssh = new Ssh();
    }

    public List<HpcHost> getHosts() {
        return hosts;
    }

    public void setHosts(List<HpcHost> hosts) {
        this.hosts = hosts;
    }

    public Ssh getSsh() {
        return ssh;
    }

    public void setSsh(Ssh ssh) {
        this.ssh = ssh;
    }

    public static class Ssh {

        private String username;
        private int port = 22;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
