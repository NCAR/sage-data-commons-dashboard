package edu.sage.datacommonsdashboard.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
            throw new IOException("File not found: " + filePath + fileName);
        }
    }

    @Override
    public String readFileWithPath(String fileName) throws IOException {

//        System.out.println("===FileRepositoryImpl data filePath: " + filePath + ", fileName: " + fileName);

        if (verifyFilePath(filePath, fileName)) {

            Resource resource = resourceLoader.getResource("file:" + filePath + fileName);

            if (resource.exists()) {
                return new String(Files.readAllBytes(Paths.get(resource.getURI())));
            } else {
                throw new IOException("File not found: " + filePath + fileName);
            }

        } else {
            throw new IOException("The file path and name are NOT correctly separated: "+ filePath + fileName);
        }

    }

    public static boolean verifyFilePath(String filePath, String fileName) throws IOException {
        Path path = Paths.get(filePath);
        Path fullPath = path.resolve(fileName);

        // Check whether the normalized full path contains the expected separation
        String expectedSeparator = System.getProperty("file.separator");
        String fullPathStr = fullPath.toString();

        // Ensure the file exists (or can be accessed)
        if (Files.exists(fullPath) || Files.notExists(fullPath)) {
            return fullPathStr.contains(expectedSeparator + fileName);
        }
        return false;
    }



}