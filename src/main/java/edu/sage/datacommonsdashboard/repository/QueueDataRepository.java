package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.model.QueueData;

import java.util.List;

public interface QueueDataRepository {

    String getCasperQstatDataText();

    String getDerechoQstatDataText();

    String getCasperQstatDataJson();

    String getDerechoQstatDataJson();

    QueueData createQueueRow();

    List<String> convertTextToJson();
}
