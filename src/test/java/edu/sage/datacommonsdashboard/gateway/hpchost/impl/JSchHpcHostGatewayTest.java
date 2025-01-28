package edu.sage.datacommonsdashboard.gateway.hpchost.impl;

import com.jcraft.jsch.HostKeyRepository;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import edu.sage.datacommonsdashboard.gateway.hpchost.SshAvailableDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JSchHpcHostGatewayTest {

    @Mock
    private Session session;

    @Mock
    private JSchSessionFactory factory;

    @Test
    public void given_ssh_available_details__when_isSshAccessible__then_return_true() throws JSchException {

        // setup
        HostKeyRepository mockHostKeyRepository = mock(HostKeyRepository.class);

        JSchHpcHostGateway.HpcHostRequestsSshAuthentication exception = new JSchHpcHostGateway.HpcHostRequestsSshAuthentication("password:");
        when(this.session.getHostKeyRepository()).thenReturn(mockHostKeyRepository);
        doThrow(exception).when(session).connect();

        when(this.factory.create("username", "host.name", 22)).thenReturn(session);

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