package edu.sage.datacommonsdashboard.gateway.hpchost.impl;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchSessionFactory {

    public JSchSessionFactory() {

    }

    public Session create(String username, String host, int port) {

        try {

            // JSch is most likely NOT THREAD SAFE.  Create a new instance every request.
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
