package edu.sage.datacommonsdashboard.repository;

import java.io.IOException;

public interface JobRepository {

    String getCasperQstatJobsText() throws IOException;

    String getCasperQstatJobsJson() throws IOException;

    String getCasperQstatQueueText() throws IOException;

    String getCasperQstatQueueJson() throws IOException;

    String getDerechoQstatQueueText() throws IOException;

    String getDerechoQstatQueueJson() throws IOException;

    String getDerechoQstatJobsText() throws IOException;

    String getDerechoQstatJobsJson() throws IOException;
}
