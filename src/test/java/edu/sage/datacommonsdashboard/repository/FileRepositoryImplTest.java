package edu.sage.datacommonsdashboard.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileRepositoryImplTest {

    @InjectMocks
    private FileRepositoryImpl fileRepositoryImpl;

    @BeforeEach
    void setUp() {

    }

    @Test
    void given_file_exists__when_verify_file_path__then_true() throws IOException {

        String filePath = "/test/path";
        String fileName = "testfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            filesMock.when(() -> Files.exists(fullPath)).thenReturn(true);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(false);

            boolean result = FileRepositoryImpl.verifyFilePath(filePath, fileName);

            assertTrue(result);
        }
    }

    @Test
    void given_file_does_not_exist__when_verify_file_path__then_false() throws IOException {

        String filePath = "/test/path";
        String fileName = "wrongfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            // Simulating that the file does not exist
            filesMock.when(() -> Files.exists(fullPath)).thenReturn(false);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(true);

            boolean result = FileRepositoryImpl.verifyFilePath(filePath, fileName);

            assertFalse(result);
        }
    }

    @Test
    void readFileWithPath_success() throws IOException {

    }

    @Test
    void readFileWithPath_fileNotFound() {

    }
}