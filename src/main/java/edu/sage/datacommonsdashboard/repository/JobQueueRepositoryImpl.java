package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.exception.FileNotReadableException;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.util.JobDataJsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class JobQueueRepositoryImpl implements JobQueueRepository {

    @Value("${casper.qstat.jobs.json}")
    private String casperQstatJobsJson;

    @Value("${casper.qstat.jobs.txt}")
    private String casperQstatJobsTxt;

    @Value("${casper.qstat.queue.json}")
    private String casperQstatQueueJson;

    @Value("${casper.qstat.queue.txt}")
    private String casperQstatQueueTxt;

    @Value("${derecho.qstat.jobs.json}")
    private String derechoQstatJobsJson;

    @Value("${derecho.qstat.jobs.txt}")
    private String derechoQstatJobsTxt;

    @Value("${derecho.qstat.queue.json}")
    private String derechoQstatQueueJson;

    @Value("${derecho.qstat.queue.txt}")
    private String derechoQstatQueueTxt;

    private final ResourceLoader resourceLoader;
    private final JobDataJsonConverter jobDataJsonConverter;

    // Expect trailing /
    private final String filePath;

    private static final Logger logger = LoggerFactory.getLogger(JobQueueRepositoryImpl.class);

    public JobQueueRepositoryImpl(ResourceLoader resourceLoader, JobDataJsonConverter jobDataJsonConverter,
                                  @Value("${dashboard.queue.file.path}") String filePath) {

        this.resourceLoader = resourceLoader;
        this.jobDataJsonConverter = jobDataJsonConverter;
        this.filePath = filePath;
    }

    @Override
    public String getCasperQstatJobsText() throws FileNotReadableException {
        return this.readFileWithPath(casperQstatJobsTxt);
    }

    @Override
    public JobData getCasperQstatJobsJson() throws FileNotReadableException {
        String jsonData = this.readFileWithPath(casperQstatJobsJson);
        return jobDataJsonConverter.convertJsonToJobData(jsonData);
    }

    @Override
    public String getCasperQstatQueueText() throws FileNotReadableException {
        return this.readFileWithPath(casperQstatQueueTxt);
    }

    @Override
    public JobData getCasperQstatQueueJson() throws FileNotReadableException {
        String jsonData = this.readFileWithPath(casperQstatQueueJson);
        return jobDataJsonConverter.convertJsonToJobData(jsonData);
    }

    @Override
    public String getDerechoQstatJobsText() throws FileNotReadableException {
        return this.readFileWithPath(derechoQstatJobsTxt);
    }

    @Override
    public JobData getDerechoQstatJobsJson() throws FileNotReadableException {
        String jsonData = this.readFileWithPath(derechoQstatJobsJson);
        return jobDataJsonConverter.convertJsonToJobData(jsonData);
    }

    @Override
    public String getDerechoQstatQueueText() throws FileNotReadableException {
        return this.readFileWithPath(derechoQstatQueueTxt);
    }

    @Override
    public JobData getDerechoQstatQueueJson() throws FileNotReadableException {
        String jsonData = readFileWithPath(derechoQstatQueueJson);
        return jobDataJsonConverter.convertJsonToJobData(jsonData);
    }

    protected boolean verifyFilePath(String filePath, String fileName) throws FileNotReadableException {
        if (filePath == null || fileName == null) {
            throw new FileNotReadableException("File path or file name is null");
        }

        Path path = Paths.get(filePath);
        Path fullPath = path.resolve(fileName);

        // Check whether the normalized full path contains the expected separation
        String expectedSeparator = System.getProperty("file.separator");
        String fullPathStr = fullPath.toString();

        // Ensure the file exists and can be accessed
        if (Files.exists(fullPath) && Files.isReadable(fullPath)) {
            return fullPathStr.contains(expectedSeparator + fileName);
        }
        return false;
    }

    protected String readFileWithPath(String fileName) throws FileNotReadableException {
        if (filePath == null) {
            throw new FileNotReadableException("File path is not set.");
        }

        if (verifyFilePath(filePath, fileName)) {
            Resource resource = resourceLoader.getResource("file:" + filePath + fileName);
            if (resource.exists()) {
                try {
                    return new String(Files.readAllBytes(Paths.get(resource.getURI())));
                } catch (IOException ex) {
                    logger.error("Error reading file: {}", fileName, ex);
                    throw new FileNotReadableException("Error reading file: " + filePath + fileName, ex);
                }
            } else {
                throw new FileNotReadableException("Resource does not exist: " + filePath + fileName);
            }
        } else {
            throw new FileNotReadableException("File cannot be located: " + filePath + fileName);
        }
    }

}