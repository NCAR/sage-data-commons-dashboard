package edu.sage.datacommonsdashboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class FileRepository {

    private final ResourceLoader resourceLoader;

    public FileRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${file.path.casper.json}")
    private String filePath;

    public String readFileFromResources(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + fileName);
        }
    }

    public String readFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public String readFileWithPath(String filePath) throws IOException {

        Resource resource = resourceLoader.getResource("file:" + filePath);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + filePath);
        }
    }
}