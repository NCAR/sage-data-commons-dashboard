package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.gateway.hpchost.impl.SshAvailableDetailsImpl;

import java.util.function.Function;

public interface SshAvailableDetails {

    String getHostname();

    String getUsername();

    int getPort();

    String getHostKey();

    String getExpectedPrompt();

    static SshAvailableDetails of(Function<SshAvailableDetails.Builder, SshAvailableDetails.Builder> f) {
        return f.apply(new SshAvailableDetails.Builder()).build();
    }

    class Builder {

        private String hostname;
        private String username;
        private int port = 22;
        private String hostKey;
        private String expectedPrompt;

        public Builder setHostname(String hostname) {

            this.hostname = hostname;
            return this;
        }

        public Builder setUsername(String username) {

            this.username = username;
            return this;
        }

        public Builder setPort(int port) {

            this.port = port;
            return this;
        }

        public Builder setHostKey(String hostKey) {

            this.hostKey = hostKey;
            return this;
        }

        public Builder setExpectedPrompt(String expectedPrompt) {

            this.expectedPrompt = expectedPrompt;
            return this;
        }

        public SshAvailableDetails build() {

            if (this.hostname == null) {
                throw new IllegalStateException("hostname cannot be null.");
            }
            if (this.username == null) {
                throw new IllegalStateException("username cannot be null.");
            }
            if (this.hostKey == null) {
                throw new IllegalStateException("hostKey cannot be null.");
            }

            return new SshAvailableDetailsImpl(this.hostname,
                    this.username,
                    this.port,
                    this.hostKey,
                    this.expectedPrompt);
        }
    }
}
