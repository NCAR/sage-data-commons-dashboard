package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

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
        } catch (JSchException e) {
            return e.getMessage().contains("Auth fail for methods") ||
                    e.getMessage().contains("Too many authentication failures");
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
            String[] str = new String[1];
            str[0] = "password";
            return str;
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
            System.out.println(message);
        }
    }
}
