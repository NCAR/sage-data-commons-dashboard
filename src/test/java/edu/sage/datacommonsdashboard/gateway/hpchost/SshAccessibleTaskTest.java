package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SshAccessibleTaskTest {

    public static final int HPCHOSTCOUNT = 3;

    @Mock
    private HpcHostRepository hpcHostRepository;

    @Mock
    private HpcHostGatewayRepository hpcHostGatewayRepository;
    
    @InjectMocks
    private SshAccessibleTask task;

    private List<HpcHost> hosts;
    private List<HpcHostGateway> gateways;

    @BeforeEach
    void setUp() {
        setUpHpcHosts();
        setUpHpcHostGateways();
    }

    private void setUpHpcHosts() {
        hosts = new ArrayList<>();

        for(int i = 0; i < HPCHOSTCOUNT; i++) {
            HpcHost hpcHost = mock(HpcHost.class);
            hosts.add(hpcHost);
        }

        when(hpcHostRepository.getAll()).thenReturn(hosts);
    }

    private void setUpHpcHostGateways() {
        gateways = new ArrayList<>();

        for(int i = 0; i < HPCHOSTCOUNT; i++) {
            HpcHostGateway hpcHostGateway = mock(HpcHostGateway.class);

            when(hpcHostGateway.isSshAccessible()).thenReturn(true);
            when(hpcHostGatewayRepository.get(hosts.get(i))).thenReturn(hpcHostGateway);

            gateways.add(hpcHostGateway);
        }

        HpcHostGateway first = gateways.get(0);
        when(first.isSshAccessible()).thenReturn(false);
    }

    @Test
    void given_hosts__when_execute__then_call_getAll_on_hpchostrepo() {
        task.execute();
        verify(hpcHostRepository).getAll();
    }

    @Test
    void given_hosts__when_execute__then_call_get_on_hpchostgatewayrepo_each_hpchost() {
        task.execute();
        hosts.forEach(host -> verify(hpcHostGatewayRepository).get(host));
    }

    @Test
    void given_hosts__when_execute__then_isSshAccessible_called_on_each_hpchostgatewayrepo() {
        task.execute();
        gateways.forEach(gateway -> verify(gateway).isSshAccessible());
    }

    @Test
    void given_hosts__when_execute__then_host_status_matches_gateway_isSshAccessible() {
        task.execute();

        HpcHost first = hosts.get(0);
        verify(first).setStatus(HpcHost.Status.OFFLINE);

        for(int i = 1; i < HPCHOSTCOUNT; i++) {
            verify(hosts.get(i)).setStatus(HpcHost.Status.ONLINE);
        }
    }
}