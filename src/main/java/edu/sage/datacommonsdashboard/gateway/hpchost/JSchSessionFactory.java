package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchSessionFactory {

    private final String host;
    private final int port;
    private final String user;

    public JSchSessionFactory() {

        this.host = null;
        this.port = 22;
        this.user = null;
    }

    public JSchSessionFactory(String host, int port, String user) {
        this.host = host;
        this.port = port;
        this.user = user;
    }

    public Session create() {
        try {
            // JSch is most likely NOT THREAD SAFE.  Create a new instance every request.
            JSch jSch = new JSch();
            Session session = jSch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            return session;
        } catch (JSchException e) {
            throw new JSchSessionCreationFailure(e);
        }
    }

    public Session create(String username, String host, int port) {

        try {

            JSch jSch = new JSch();
            return jSch.getSession(username, host, port);

        } catch (JSchException e) {

            throw new JSchSessionCreationFailure(e);
        }
    }

    public static class JSchSessionCreationFailure extends RuntimeException {

        public JSchSessionCreationFailure(Throwable cause) {
            super(cause);
        }
    }
}
