package edu.sage.datacommonsdashboard.service;

import edu.sage.datacommonsdashboard.model.QueueData;

public interface QueueDataService {

    String getCasperQstatDataText();

    String getDerechoQstatData();

    QueueData createQueueRow();
}
