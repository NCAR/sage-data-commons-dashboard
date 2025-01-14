package edu.sage.datacommonsdashboard.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HpcHostControllerTest {

    @Mock
    private HpcHostRepository repository;

    @Mock
    private HpcHostTransformer transformer;

    @InjectMocks
    private HpcHostController controller;

    private List<HpcHost> hosts;
    private List<HpcHostModel> models = new ArrayList<>();

    @BeforeEach
    void setUp() {
        setupHpcHostsAndModels();
        when(repository.getAll()).thenReturn(hosts);
    }

    private void setupHpcHostsAndModels() {
        hosts = new ArrayList<>();
        models = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            HpcHost host = mock(HpcHost.class);
            HpcHostModel model = mock(HpcHostModel.class);

            when(transformer.transform(host)).thenReturn(model);

            hosts.add(host);
            models.add(model);
        }
    }

    @Test
    void given_hosts__when_getHpcHosts__repository_getAll_called() {
        controller.getHpcHosts();
        verify(repository).getAll();
    }

    @Test
    void given_hosts__when_getHpcHosts__transformer_called_on_each_host() {
        controller.getHpcHosts();
        hosts.forEach(host -> verify(transformer).transform(host));
    }

    @Test
    void given_hosts__when_getHpcHosts__transformer_models_returned() {
        List<HpcHostModel> results = controller.getHpcHosts();
        assertEquals(models.size(), results.size());
        assertTrue(models.containsAll(results));
    }
}
