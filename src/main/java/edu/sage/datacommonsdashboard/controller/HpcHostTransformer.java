package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.model.HpcHost;
import org.springframework.stereotype.Component;

@Component
public class HpcHostTransformer {

    public HpcHostTransformer() {
    }

    public HpcHostModel transform(HpcHost hpcHost) {
        HpcHostModel model = new HpcHostModel();

        model.setHostname(hpcHost.getHostname());
        model.setFqdn(hpcHost.getFqdn());

        switch (hpcHost.getStatus()) {
        case ONLINE -> model.setStatus("Online");
        default -> model.setStatus("Offline");
        }

        return model;
    }
}
