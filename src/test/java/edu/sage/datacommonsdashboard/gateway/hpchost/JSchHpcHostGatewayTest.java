package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JSchHpcHostGatewayTest {

    @Mock
    private Session session;

    @Mock
    private JSchSessionFactory factory;

    @InjectMocks
    private JSchHpcHostGateway gateway;

    @BeforeEach
    void setUp() {
        when(factory.create()).thenReturn(session);
    }

    @Test
    void given_no_exception_from_session__when_isSshAccessible__then_accessible() {
        assertTrue(gateway.isSshAccessible());
    }

    @Test
    void given_unknown_jSchException_from_session__when_isSshAccessible__then_inaccessible() throws JSchException {
        JSchException jSchException = new JSchException("unknown reason");
        doThrow(jSchException).when(session).connect();
        assertFalse(gateway.isSshAccessible());
    }

    @Test
    void given_RuntimeException_from_factory__when_isSshAccessible__then_inaccessible() {
        RuntimeException exception = new RuntimeException("unknown reason");
        when(factory.create()).thenThrow(exception);
        assertFalse(gateway.isSshAccessible());
    }

    @Test
    void given_known_authn_jSchException_from_session_too_many_authn___when_isSshAccessible__then_accessible() throws JSchException {
        JSchException jSchException = new JSchException("SSH_MSG_DISCONNECT: 2 Too many authentication failures");
        doThrow(jSchException).when(session).connect();
        assertTrue(gateway.isSshAccessible());
    }

    @Test
    void given_known_authn_jSchException_from_session_keyboard_interactive__when_isSshAccessible__then_accessible() throws JSchException {
        JSchException jSchException = new JSchException("Auth fail for methods 'keyboard-interactive'");
        doThrow(jSchException).when(session).connect();
        assertTrue(gateway.isSshAccessible());
    }
}