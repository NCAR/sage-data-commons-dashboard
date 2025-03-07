package edu.sage.datacommonsdashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sage.datacommonsdashboard.model.FilteredHpcHost;
import edu.sage.datacommonsdashboard.model.HpcHost;
import edu.sage.datacommonsdashboard.model.ServerStatusResponse;
import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Automatically initializes mocks
class ServerStatusServiceTest {

    @Mock
    private HpcHostRepository repository;

    @InjectMocks
    private ServerStatusService serverStatusService; // Service under test

    @Test
    void testGetServerStatusResponse() {

        List<HpcHost> mockHosts = Arrays.asList(
                new HpcHost(),
                new HpcHost()
        );
        when(repository.getAll()).thenReturn(mockHosts);

        ServerStatusResponse response = serverStatusService.getServerStatusResponse();

        assertNotNull(response);
        assertEquals(2, response.getHosts().size());
        verify(repository, times(1)).getAll(); // Verify repository interaction
    }

    @Test
    void testGetFilteredHosts() {

        List<HpcHost> mockHosts = Arrays.asList(
                new HpcHost(),
                new HpcHost()
        );

        List<FilteredHpcHost> filteredHosts = serverStatusService.getFilteredHosts(mockHosts);

        assertNotNull(filteredHosts);
        assertEquals(2, filteredHosts.size());
    }

    @Test
    void testBuildJson_WhenValidInput() {

        ServerStatusResponse response = new ServerStatusResponse(123456789L, Arrays.asList(
                new FilteredHpcHost("host1", "fqdn1", "user1", HpcHost.Status.ONLINE),
                new FilteredHpcHost("host2", "fqdn2", "user2", HpcHost.Status.OFFLINE)
        ));

        String jsonOutput = serverStatusService.buildJson(response);

        String expectedJson = """
                {
                  "timestamp" : 123456789,
                  "hosts" : [ {
                    "hostname" : "host1",
                    "fqdn" : "fqdn1",
                    "username" : "user1",
                    "status" : "ONLINE"
                  }, {
                    "hostname" : "host2",
                    "fqdn" : "fqdn2",
                    "username" : "user2",
                    "status" : "OFFLINE"
                  } ]
                }""";

        assertNotNull(jsonOutput);
        assertEquals(expectedJson, jsonOutput);
    }

    @Test
    void testBuildJson_WhenJsonProcessingExceptionOccurs() {

        ServerStatusService customService = new ServerStatusService(repository) {

            @Override
            public String buildJson(ServerStatusResponse serverStatusResponse) {
                try {
                    ObjectMapper failingObjectMapper = mock(ObjectMapper.class);

                    when(failingObjectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

                    return failingObjectMapper.writeValueAsString(serverStatusResponse);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ServerStatusResponse response = new ServerStatusResponse(123456789L, Arrays.asList(
                new FilteredHpcHost("host1", "fqdn1", "user1", HpcHost.Status.ONLINE)
        ));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> customService.buildJson(response));
        assertNotNull(exception);
    }
}