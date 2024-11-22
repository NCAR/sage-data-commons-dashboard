package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.model.QueueData;

import java.io.IOException;
import java.util.List;

public interface FileRepository {

    String getCasperQstatJobsText() throws IOException;

    String getCasperQstatJobsJson() throws IOException;

    String getCasperQstatQueueText() throws IOException;

    String getCasperQstatQueueJson() throws IOException;

    String getDerechoQstatQueueText() throws IOException;

    String getDerechoQstatQueueJson()throws IOException;

    QueueData createQueueRow();

    List<String> convertTextToJson();
}
