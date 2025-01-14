package edu.sage.datacommonsdashboard.query;

import edu.sage.datacommonsdashboard.model.HpcHost;

public class HpcHostTransformer {

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
