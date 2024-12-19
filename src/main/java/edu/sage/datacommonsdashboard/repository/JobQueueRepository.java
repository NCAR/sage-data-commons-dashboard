package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.exception.FileNotReadableException;
import edu.sage.datacommonsdashboard.model.JobData;

public interface JobQueueRepository {

    String getCasperQstatJobsText() throws FileNotReadableException;

    JobData getCasperQstatJobsJson() throws FileNotReadableException;

    String getCasperQstatQueueText() throws FileNotReadableException;

    JobData getCasperQstatQueueJson() throws FileNotReadableException;

    String getDerechoQstatQueueText() throws FileNotReadableException;

    JobData getDerechoQstatQueueJson() throws FileNotReadableException;

    String getDerechoQstatJobsText() throws FileNotReadableException;

    JobData getDerechoQstatJobsJson() throws FileNotReadableException;
}
