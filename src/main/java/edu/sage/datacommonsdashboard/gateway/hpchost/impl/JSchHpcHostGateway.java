package edu.sage.datacommonsdashboard.gateway.hpchost.impl;

import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import edu.sage.datacommonsdashboard.gateway.hpchost.HpcHostGateway;
import edu.sage.datacommonsdashboard.gateway.hpchost.SshAvailableDetails;

import java.util.Arrays;
import java.util.Base64;

public class JSchHpcHostGateway implements HpcHostGateway {

    private final JSchSessionFactory sessionFactory;

    public JSchHpcHostGateway(JSchSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean isSshAccessible(SshAvailableDetails request) {

        boolean isAvailable = false;

        Session session = null;

        try {
            session = sessionFactory.create(request.getUsername(),
                    request.getHostname(),
                    request.getPort());
            session.setUserInfo(new InnerUserInfo(request.getExpectedPrompt()));

            // https://dentrassi.de/2015/07/13/programmatically-adding-a-host-key-with-jsch/
            // Adding host key/fingerprint check.
            byte [] key = Base64.getDecoder().decode(request.getHostKey());
            HostKey hostKey = new HostKey(request.getHostname(), key);
            session.getHostKeyRepository().add(hostKey, null);

            session.connect();

        } catch (HpcHostRequestsSshAuthentication e) {

            isAvailable = true;

        } catch (Exception ignored) {

        } finally {

            if (session != null) {

                session.disconnect();
            }
        }

        return isAvailable;
    }

    public static class InnerUserInfo implements UserInfo, UIKeyboardInteractive {

        private final String expectedPrompt;

        public InnerUserInfo(String expectedPrompt) {
            this.expectedPrompt = expectedPrompt;
        }

        @Override
        public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {

            if (prompt.length > 0 && prompt[0].trim().equals(this.expectedPrompt.trim())) {
                throw new HpcHostRequestsSshAuthentication("prompt is: " + Arrays.toString(prompt));
            } else {
                throw new HpcHostGatewayPromptException("prompt is: " + Arrays.toString(prompt) + "expected: " + this.expectedPrompt);
            }
        }

        @Override
        public String getPassphrase() {
            return "";
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public boolean promptPassword(String message) {
            return false;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return false;
        }

        @Override
        public boolean promptYesNo(String message) {
            return false;
        }

        @Override
        public void showMessage(String message) {
        }
    }


    public static class HpcHostRequestsSshAuthentication extends RuntimeException {

        public HpcHostRequestsSshAuthentication(String message) {
            super(message);
        }
    }

    public static class HpcHostGatewayPromptException extends RuntimeException {

        public HpcHostGatewayPromptException(String message) {
            super(message);
        }
    }
}
