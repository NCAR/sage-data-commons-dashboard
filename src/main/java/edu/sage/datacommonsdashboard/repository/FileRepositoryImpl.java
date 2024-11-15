package edu.sage.datacommonsdashboard.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final ResourceLoader resourceLoader;

    public FileRepositoryImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${dashboard.queue.file.path}")
    private String filePath;

    @Override
    public String readFileFromResources(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + fileName);
        }
    }

    @Override
    public String readFileWithPath(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("file:" + filePath + fileName);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + fileName);
        }
    }

    private String readFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}