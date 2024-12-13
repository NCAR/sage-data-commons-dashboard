package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.service.FileNotReadableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JobRepositoryTest {

    @TempDir
    Path tempDir;

    @InjectMocks
    private JobRepositoryImpl jobRepositoryImpl;

    private static final String SAMPLE_CASPER_FILE_CONTENT = "Sample Casper file content";
    private static final String SAMPLE_DERECHO_FILE_CONTENT = "Sample Derecho file content";
    private static final String CASPER_FILE_NAME = "casper_qstat_jobs.txt";
    private static final String DERECHO_FILE_NAME = "derecho_qstat_jobs.txt";

    private Resource mockResource = mock(Resource.class);
    private ResourceLoader mockResourceLoader = mock(ResourceLoader.class);

    @BeforeEach
    void setUp() {

        jobRepositoryImpl = new JobRepositoryImpl(mockResourceLoader);
        jobRepositoryImpl.filePath = tempDir.toString() + "/";
    }

    @Test
    void given_file_exists__when_verify_file_path__then_true() throws IOException {

        String filePath = "/test/path";
        String fileName = "testfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            // Simulate that the file exists
            filesMock.when(() -> Files.exists(fullPath)).thenReturn(true);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(false);

            boolean result = jobRepositoryImpl.verifyFilePath(filePath, fileName);

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

            boolean result = jobRepositoryImpl.verifyFilePath(filePath, fileName);

            assertFalse(result);
        }
    }

    @Test
    public void given_text_file_resource_in_file_path__when_get_contents__then_correct_string_contents_returned() throws IOException {

        // Create temp file and write content
        Resource mockResource = mock(Resource.class);
        Path tempFile = Files.createFile(tempDir.resolve(DERECHO_FILE_NAME));
        Files.writeString(tempFile, SAMPLE_DERECHO_FILE_CONTENT);

        //when(mockResourceLoader.getResource(anyString())).thenReturn(mockResource);
        when(mockResourceLoader.getResource("file:" + tempDir.toString() + "/" + DERECHO_FILE_NAME)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(true);
        when(mockResource.getURI()).thenReturn(tempFile.toUri());

        // Execute
        String result = jobRepositoryImpl.getDerechoQstatJobsText();

        // Assert
        assertNotEquals(SAMPLE_CASPER_FILE_CONTENT, result);
        assertEquals(SAMPLE_DERECHO_FILE_CONTENT, result);

        // Clean up
        Files.deleteIfExists(tempFile);
    }

    @Test
    void given_text_file_resource_in_file_path__when_not_found__then_file_path_error() throws IOException {

        // Mock resource loader behavior
        Resource mockResource = mock(Resource.class);
        when(mockResourceLoader.getResource("file:" + tempDir.toString() + "/" + DERECHO_FILE_NAME)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(false);

        // Verify method throws IOException
        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobRepositoryImpl.getDerechoQstatJobsText();
        });

        assertEquals("File cannot be located: " + tempDir.toString() + "/" + DERECHO_FILE_NAME, exception.getMessage());
    }

    @Test
    void given_text_file_in_file_path__when_file_path_is_null__then_file_path_not_set_error() {

        jobRepositoryImpl.filePath = null;

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobRepositoryImpl.getDerechoQstatJobsText();
        });

        assertEquals("File path is not set.", exception.getMessage());
    }

    @Test
    void given_text_file_resource__when_file_path_is_null__then_resource_null_error() {

        jobRepositoryImpl.filePath = null;

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobRepositoryImpl.readFileFromResources(CASPER_FILE_NAME);
        });

        assertEquals("Resource is null for file: casper_qstat_jobs.txt", exception.getMessage());
    }

    @Test
    void given_text_file_in_path__when_file_path_is_invalid__then_file_path_error() {

        jobRepositoryImpl.filePath = "invalidPath/";

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobRepositoryImpl.readFileWithPath(CASPER_FILE_NAME);
        });

        assertEquals("File cannot be located: invalidPath/casper_qstat_jobs.txt", exception.getMessage());
    }

    @Test
    void given_text_file_resource__when_file_path_is_invalid__then_file_path_error() throws IOException {

        when(mockResourceLoader.getResource("file:" + tempDir.toString() + "/" + CASPER_FILE_NAME)).thenReturn(mock(Resource.class));

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobRepositoryImpl.readFileFromResources(CASPER_FILE_NAME);
        });

        assertEquals("Resource is null for file: " + CASPER_FILE_NAME, exception.getMessage());
    }

    @Test
    void when_read_file_with_path__if_file_exists__then_success() throws IOException {

        // Create temp file and write content
        Path tempFile = Files.createFile(tempDir.resolve(CASPER_FILE_NAME));
        Files.writeString(tempFile, CASPER_FILE_NAME);

        // Mock resource loader behavior
        Resource mockResource = mock(Resource.class);
        when(mockResourceLoader.getResource("file:" + tempDir.toString() + "/" + CASPER_FILE_NAME)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(true);
        when(mockResource.getURI()).thenReturn(tempFile.toUri());

        // Verify method output
        String result = jobRepositoryImpl.readFileWithPath(CASPER_FILE_NAME);
        assertEquals(CASPER_FILE_NAME, result);

        // Clean up temp file
        Files.deleteIfExists(tempFile);
    }

}