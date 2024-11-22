package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.model.QueueData;

import java.io.IOException;
import java.util.List;

public interface FileRepository {

    String readFileFromResources(String fileName) throws IOException;

    String readFileWithPath(String filePath) throws IOException;


    String getCasperQstatDataText();

    String getDerechoQstatDataText();

    String getCasperQstatDataJson();

    String getDerechoQstatDataJson();

    QueueData createQueueRow();

    List<String> convertTextToJson();
}
