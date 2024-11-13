package edu.sage.datacommonsdashboard.service;

import edu.sage.datacommonsdashboard.model.QueueData;

import java.util.List;

public interface QueueDataService {

    String getCasperQstatDataText();

    String getDerechoQstatDataText();

    String getCasperQstatDataJson();

    String getDerechoQstatDataJson();

    QueueData createQueueRow();

    List<String> convertTextToJson();
}
