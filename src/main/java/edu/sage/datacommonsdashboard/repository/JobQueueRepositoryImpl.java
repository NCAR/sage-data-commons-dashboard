package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.exception.FileNotReadableException;
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

    public static final String CASPER_QSTAT_JOBS_JSON = "casper_qstat_jobs.json";
    public static final String CASPER_QSTAT_JOBS_TXT = "casper_qstat_jobs.txt";
    public static final String CASPER_QSTAT_QUEUE_TXT = "casper_qstat_queue.txt";
    public static final String CASPER_QSTAT_QUEUE_JSON = "casper_qstat_queue.json";
    public static final String DERECHO_QSTAT_JOBS_TXT = "derecho_qstat_jobs.txt";
    public static final String DERECHO_QSTAT_JOBS_JSON = "derecho_qstat_jobs.json";
    public static final String DERECHO_QSTAT_QUEUE_TXT = "derecho_qstat_queue.txt";
    public static final String DERECHO_QSTAT_QUEUE_JSON = "derecho_qstat_queue.json";

    private final ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(JobQueueRepositoryImpl.class);

    public JobQueueRepositoryImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${dashboard.queue.file.path}")
    protected String filePath;

    @Override
    public String getCasperQstatJobsText() throws FileNotReadableException {

        return this.readFileWithPath(CASPER_QSTAT_JOBS_TXT);
    }

    @Override
    public String getCasperQstatJobsJson() throws FileNotReadableException {

        return this.readFileWithPath(CASPER_QSTAT_JOBS_JSON);
    }

    @Override
    public String getCasperQstatQueueText() throws FileNotReadableException {

        return this.readFileWithPath(CASPER_QSTAT_QUEUE_TXT);
    }

    @Override
    public String getCasperQstatQueueJson() throws FileNotReadableException {

        return this.readFileWithPath(CASPER_QSTAT_QUEUE_JSON);
    }

    @Override
    public String getDerechoQstatJobsText() throws FileNotReadableException {

        return this.readFileWithPath(DERECHO_QSTAT_JOBS_TXT);
    }

    @Override
    public String getDerechoQstatJobsJson() throws FileNotReadableException {

        return this.readFileWithPath(DERECHO_QSTAT_JOBS_JSON);
    }

    @Override
    public String getDerechoQstatQueueText() throws FileNotReadableException {

        return this.readFileWithPath(DERECHO_QSTAT_QUEUE_TXT);
    }

    @Override
    public String getDerechoQstatQueueJson() throws FileNotReadableException {

        return this.readFileWithPath(DERECHO_QSTAT_QUEUE_JSON);
    }

    public boolean verifyFilePath(String filePath, String fileName) throws FileNotReadableException {

        if (filePath == null || fileName == null) {
            throw new FileNotReadableException("File path or file name is null");
        }

        Path path = Paths.get(filePath);
        Path fullPath = path.resolve(fileName);

        // Check whether the normalized full path contains the expected separation
        String expectedSeparator = System.getProperty("file.separator");
        String fullPathStr = fullPath.toString();

        // Ensure the file exists (or can be accessed)
        if (Files.exists(fullPath) ) {
            return fullPathStr.contains(expectedSeparator + fileName);
        }
        return false;
    }

    // Deprecated?
    protected String readFileFromResources(String fileName) throws FileNotReadableException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource == null) {
            throw new FileNotReadableException("Resource is null for file: " + fileName);
        }

        if (resource.exists()) {
            try {
                return new String(Files.readAllBytes(Paths.get(resource.getURI())));
            } catch (IOException e) {
                throw new RuntimeException("Error reading file: " + fileName, e);
            }
        } else {
            throw new FileNotReadableException("Resource does not exist: " + filePath + fileName);
        }
    }

    protected String readFileWithPath(String fileName) throws FileNotReadableException {

       System.out.println("===JobQueueRepositoryImpl data filePath: " + filePath + ", fileName: " + fileName);

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
                    throw new FileNotReadableException("Error reading file: " + fileName, ex);
                }

            } else {
                throw new FileNotReadableException("Resource does not exist: " + filePath + fileName);
            }

        } else {
            throw new FileNotReadableException("File cannot be located: "+ filePath + fileName);
        }
    }

}