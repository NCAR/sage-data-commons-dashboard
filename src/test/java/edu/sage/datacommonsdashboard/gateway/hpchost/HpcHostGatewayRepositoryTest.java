package edu.sage.datacommonsdashboard.gateway.hpchost;

import edu.sage.datacommonsdashboard.model.HpcHost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HpcHostGatewayRepositoryTest {

    @Mock
    private HpcHostGatewayFactory factory;

    @InjectMocks
    private HpcHostGatewayRepository repository;

    private UUID uuid;
    private HpcHost host;
    private HpcHostGateway gateway;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        host = mock(HpcHost.class);
        gateway = mock(HpcHostGateway.class);

        when(host.getId()).thenReturn(uuid);
        when(factory.create(host)).thenReturn(gateway);
    }

    @Test
    void given_host__when_get_called_many_times_for_same_host__factory_called_once_to_create_gateway() {
        repository.get(host);
        repository.get(host);
        repository.get(host);
        verify(factory, atMostOnce()).create(host);
    }

    @Test
    void given_host__when_get_called_many_times_for_same_host__same_gateway_returned() {
        HpcHostGateway g1 = repository.get(host);
        HpcHostGateway g2 = repository.get(host);
        HpcHostGateway g3 = repository.get(host);

        assertEquals(g1, g2);
        assertEquals(g1, g3);
        assertEquals(g1, gateway);
    }
}