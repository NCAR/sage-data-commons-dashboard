package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class SshAccessibleTaskTest {

    @Test
    public void given_host__when_execute__then_host_status_set() {

        this.assertAccessible(HpcHost.Status.OFFLINE, true, HpcHost.Status.ONLINE);
        this.assertAccessible(HpcHost.Status.ONLINE, false, HpcHost.Status.OFFLINE);
    }

    private void assertAccessible(HpcHost.Status initialStatus, boolean sshAccessible, HpcHost.Status expectedStatus) {

        // setup
        HpcHost host = new HpcHost();
        host.setFqdn("test.host.name");
        host.setHostKey("host.key.signature");
        host.setUsername("username");
        host.setStatus(initialStatus);

        HpcHostRepository mockHostRepository = mock(HpcHostRepository.class);
        when(mockHostRepository.getAll()).thenReturn(Arrays.asList(host));

        HpcHostGateway mockGateway = mock(HpcHostGateway.class);
        when(mockGateway.isSshAccessible(any(SshAvailableDetails.class))).thenReturn(sshAccessible);

        // execute
        SshAccessibleTask task = new SshAccessibleTask(mockHostRepository, mockGateway);
        task.execute();

        // assert
        assertThat(host.getStatus(), is(expectedStatus));
    }
}