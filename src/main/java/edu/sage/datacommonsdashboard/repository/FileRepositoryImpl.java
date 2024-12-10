package edu.sage.datacommonsdashboard.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileRepositoryImpl implements FileRepository {

   public static final String CASPER_QSTAT_JOBS_JSON = "casper_qstat_jobs.json";
    // public static final String CASPER_QSTAT_JOBS_JSON = "summarized_casper_qstat_jobs.json";
    public static final String CASPER_QSTAT_JOBS_TXT = "casper_qstat_jobs.txt";
    public static final String CASPER_QSTAT_QUEUE_TXT = "casper_qstat_queue.txt";
    public static final String CASPER_QSTAT_QUEUE_JSON = "casper_qstat_queue.json";
    public static final String DERECHO_QSTAT_JOBS_TXT = "derecho_qstat_jobs.txt";
    public static final String DERECHO_QSTAT_JOBS_JSON = "derecho_qstat_jobs.json";
    public static final String DERECHO_QSTAT_QUEUE_TXT = "derecho_qstat_queue.txt";
    public static final String DERECHO_QSTAT_QUEUE_JSON = "derecho_qstat_queue.json";

    private final ResourceLoader resourceLoader;

    public FileRepositoryImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${dashboard.queue.file.path}")
    protected String filePath;


    @Override
    public String getCasperQstatJobsText() throws IOException {

        return this.readFileWithPath(CASPER_QSTAT_JOBS_TXT);
    }

    @Override
    public String getCasperQstatJobsJson() throws IOException {

        return this.readFileWithPath(CASPER_QSTAT_JOBS_JSON);
    }

    @Override
    public String getCasperQstatQueueText() throws IOException {

        return this.readFileWithPath(CASPER_QSTAT_QUEUE_TXT);
    }

    @Override
    public String getCasperQstatQueueJson() throws IOException {

        return this.readFileWithPath(CASPER_QSTAT_QUEUE_JSON);
    }

    @Override
    public String getDerechoQstatJobsText() throws IOException {

        return this.readFileWithPath(DERECHO_QSTAT_JOBS_TXT);
    }

    @Override
    public String getDerechoQstatJobsJson() throws IOException {

        return this.readFileWithPath(DERECHO_QSTAT_JOBS_JSON);
    }

    @Override
    public String getDerechoQstatQueueText() throws IOException {

        return this.readFileWithPath(DERECHO_QSTAT_QUEUE_TXT);
    }

    @Override
    public String getDerechoQstatQueueJson() throws IOException {

        return this.readFileWithPath(DERECHO_QSTAT_QUEUE_JSON);
    }

    public boolean verifyFilePath(String filePath, String fileName) throws FileNotFoundException {

        if (filePath == null || fileName == null) {
            throw new FileNotFoundException("File path or file name is null");
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
    protected String readFileFromResources(String fileName) throws FileNotFoundException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource == null) {
            throw new FileNotFoundException("Resource is null for file: " + fileName);
        }

        if (resource.exists()) {
            try {
                return new String(Files.readAllBytes(Paths.get(resource.getURI())));
            } catch (IOException e) {
                throw new RuntimeException("Error reading file: " + fileName, e);
            }
        } else {
            throw new FileNotFoundException("File not found: " + filePath + fileName);
        }
    }

    protected String readFileWithPath(String fileName) throws IOException {

       System.out.println("===FileRepositoryImpl data filePath: " + filePath + ", fileName: " + fileName);

        if (filePath == null) {
            throw new FileNotFoundException("File path is not set.");
        }

        if (verifyFilePath(filePath, fileName)) {

            Resource resource = resourceLoader.getResource("file:" + filePath + fileName);

            if (resource.exists()) {

                return new String(Files.readAllBytes(Paths.get(resource.getURI())));

            } else {
                throw new FileNotFoundException("File not found: " + filePath + fileName);
            }

        } else {
            throw new FileNotFoundException("File cannot be located: "+ filePath + fileName);
        }
    }

}