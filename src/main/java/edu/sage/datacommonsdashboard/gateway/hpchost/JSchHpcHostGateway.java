package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import java.util.Arrays;

public class JSchHpcHostGateway implements HpcHostGateway {

    private final JSchSessionFactory sessionFactory;

    public JSchHpcHostGateway(JSchSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean isSshAccessible() {
        Session session = null;

        try {
            session = sessionFactory.create();
            session.setUserInfo(new MyUserInfo());
            session.connect();
        } catch (HpcHostRequestsSshAuthentication e) {
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return true;
    }

    public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {

        @Override
        public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {
            throw new HpcHostRequestsSshAuthentication("prompt is: " + Arrays.toString(prompt));
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
}
