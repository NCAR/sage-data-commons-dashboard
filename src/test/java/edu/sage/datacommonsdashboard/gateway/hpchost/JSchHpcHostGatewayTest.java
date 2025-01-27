package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.HostKeyRepository;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JSchHpcHostGatewayTest {

    @Mock
    private Session session;

    @Mock
    private JSchSessionFactory factory;

    @InjectMocks
    private JSchHpcHostGateway gateway;

    @Test
    void given_no_exception_from_session__when_isSshAccessible__then_accessible() {
        when(factory.create()).thenReturn(session);
        assertTrue(gateway.isSshAccessible());
    }

    @Test
    void given_unknown_jSchException_from_session__when_isSshAccessible__then_inaccessible() throws JSchException {
        when(factory.create()).thenReturn(session);
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
    void given_HpcHostRequestsSshAuthentication_exception_from_session__when_isSshAccessible__then_accessible() throws JSchException {
        when(factory.create()).thenReturn(session);
        JSchHpcHostGateway.HpcHostRequestsSshAuthentication exception = new JSchHpcHostGateway.HpcHostRequestsSshAuthentication("password:");
        doThrow(exception).when(session).connect();
        assertTrue(gateway.isSshAccessible());
    }

    @Test
    public void given_ssh_available_details__when_isSshAccessible__then_return_true() throws JSchException {

        HostKeyRepository mockHostKeyRepository = mock(HostKeyRepository.class);

        JSchHpcHostGateway.HpcHostRequestsSshAuthentication exception = new JSchHpcHostGateway.HpcHostRequestsSshAuthentication("password:");
        when(this.session.getHostKeyRepository()).thenReturn(mockHostKeyRepository);
        doThrow(exception).when(session).connect();

        // setup
        //JSchSessionFactory mockFactory = mock(JSchSessionFactory.class);
        when(this.factory.create("username", "host.name", 22)).thenReturn(session);

        // real or mock details?
        SshAvailableDetails details = SshAvailableDetails.of(b -> b.setHostname("host.name")
                .setUsername("username")
                .setPort(22)
                .setHostKey("AAAAC3NzaC1lZDI1NTE5AAAAIEYO41qEX2Pce+OLnySNWtlKkn9okhyRCO8b5AAV9RZ+"));

        // execute
        JSchHpcHostGateway gateway1 = new JSchHpcHostGateway(this.factory);
        boolean result = gateway1.isSshAccessible(details);

        // assert
        assertThat(result, is(true));
    }
}