package edu.sage.datacommonsdashboard.service;

import edu.sage.datacommonsdashboard.model.HpcHost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HpcHostTransformerTest {

    private HpcHost host;
    private HpcHostTransformer transformer;

    @BeforeEach
    void setUp() {
        host = new HpcHost();

        host.setHostname("derecho");
        host.setFqdn("derecho.hpc.ucar.edu");

        transformer = new HpcHostTransformer();
    }

    @Test
    void given_configuration__when_transform__model_is_correct() {
        HpcHostModel model = transformer.transform(host);
        assertEquals("derecho", model.getHostname());
        assertEquals("derecho.hpc.ucar.edu", model.getFqdn());
    }

    @Test
    void given_status__when_transform__status_is_correct() {
        host.setStatus(HpcHost.Status.OFFLINE);
        assertEquals("Offline", transformer.transform(host).getStatus());

        host.setStatus(HpcHost.Status.ONLINE);
        assertEquals("Online", transformer.transform(host).getStatus());
    }
}