package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.service.FileNotReadableException;

public interface JobRepository {

    String getCasperQstatJobsText() throws FileNotReadableException;

    String getCasperQstatJobsJson() throws FileNotReadableException;

    String getCasperQstatQueueText() throws FileNotReadableException;

    String getCasperQstatQueueJson() throws FileNotReadableException;

    String getDerechoQstatQueueText() throws FileNotReadableException;

    String getDerechoQstatQueueJson() throws FileNotReadableException;

    String getDerechoQstatJobsText() throws FileNotReadableException;

    String getDerechoQstatJobsJson() throws FileNotReadableException;
}
