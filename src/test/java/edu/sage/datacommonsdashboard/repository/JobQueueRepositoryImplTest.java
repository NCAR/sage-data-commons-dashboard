package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.exception.FileNotReadableException;
import edu.sage.datacommonsdashboard.model.JobData;
import edu.sage.datacommonsdashboard.util.JobDataJsonConverter;
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
import static org.mockito.Mockito.*;

class JobQueueRepositoryImplTest {

    // Temporary directory injected by JUnit
    // This doesn't have a trailing /
    @TempDir
    Path tempDir;

    String PATH_WITH_TRAILING_SLASH;

    @InjectMocks
    private JobQueueRepositoryImpl jobQueueRepositoryImplTest;

    private static final String CASPER_FILE_NAME_TEXT = "casper_qstat_jobs.txt";
    private static final String DERECHO_FILE_NAME = "derecho_qstat_jobs.txt";
    private static final String CASPER_FILE_NAME_JSON = "casper_qstat_jobs.json";
    private static final String DERECHO_FILE_NAME_JSON = "derecho_qstat_jobs.json";

    private static final String SAMPLE_CASPER_FILE_CONTENT = "Sample Casper file content";
    private static final String SAMPLE_DERECHO_FILE_CONTENT = "Sample Derecho file content";

    private Resource mockResource = mock(Resource.class);
    private JobDataJsonConverter mockJobDataJsonConverter = mock(JobDataJsonConverter.class);
    private ResourceLoader mockResourceLoader = mock(ResourceLoader.class);


    @BeforeEach
    void setUp() {
        PATH_WITH_TRAILING_SLASH = tempDir.toString() + "/";
        jobQueueRepositoryImplTest = new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter, PATH_WITH_TRAILING_SLASH);
    }

    @Test
    void given_file_exists__when_verify_file_path__then_true()  {

        String filePath = "/test/path/";
        String fileName = "testfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            // Simulate that the file exists
            filesMock.when(() -> Files.exists(fullPath)).thenReturn(true);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(false);
            filesMock.when(() -> Files.isReadable(fullPath)).thenReturn(true);

            boolean result = jobQueueRepositoryImplTest.verifyFilePath(filePath, fileName);

            assertTrue(result);
        }
    }

    @Test
    void given_file_does_not_exist__when_verify_file_path__then_false() {

        String filePath = "/test/path";
        String fileName = "wrongfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            // Simulating that the file does not exist
            filesMock.when(() -> Files.exists(fullPath)).thenReturn(false);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(true);
            filesMock.when(() -> Files.isReadable(fullPath)).thenReturn(true);

            boolean result = jobQueueRepositoryImplTest.verifyFilePath(filePath, fileName);

            assertFalse(result);
        }
    }

    @Test
    void given_file_exists__when_verify_file_path_file_not_readable__then_false() {

        String filePath = "/test/path/";
        String fileName = "testfile.txt";

        Path fullPath = Paths.get(filePath).resolve(fileName);

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {

            // Simulate that the file exists
            filesMock.when(() -> Files.exists(fullPath)).thenReturn(true);
            filesMock.when(() -> Files.notExists(fullPath)).thenReturn(false);
            filesMock.when(() -> Files.isReadable(fullPath)).thenReturn(false);

            boolean result = jobQueueRepositoryImplTest.verifyFilePath(filePath, fileName);

            assertFalse(result);
        }
    }

    @Test
    public void given_text_file_resource_in_file_path__when_get_string_contents__then_correct_string_contents_returned() throws IOException {

        // Create temp file and write content
        Path tempFile = Files.createFile(tempDir.resolve(DERECHO_FILE_NAME));
        Files.writeString(tempFile, SAMPLE_DERECHO_FILE_CONTENT);

        //when(mockResourceLoader.getResource(anyString())).thenReturn(mockResource);
        when(mockResourceLoader.getResource("file:" + PATH_WITH_TRAILING_SLASH + DERECHO_FILE_NAME)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(true);
        when(mockResource.getURI()).thenReturn(tempFile.toUri());

        // Get String results
        String result = jobQueueRepositoryImplTest.getDerechoQstatJobsText();

        assertNotEquals(SAMPLE_CASPER_FILE_CONTENT, result);
        assertEquals(SAMPLE_DERECHO_FILE_CONTENT, result);

        // Clean up
        Files.deleteIfExists(tempFile);
    }

    @Test
    void given_text_file_resource_in_file_path__when_file_not_found__then_file_path_error() throws IOException {

        // Mock resource loader behavior
        when(mockResourceLoader.getResource("file:" + PATH_WITH_TRAILING_SLASH + DERECHO_FILE_NAME)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(false);

        // Verify method throws IOException
        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobQueueRepositoryImplTest.getDerechoQstatJobsText();
        });

        // TODO: Not sure message checking is a great test?
        assertEquals("File cannot be located: " + PATH_WITH_TRAILING_SLASH + DERECHO_FILE_NAME, exception.getMessage());
    }

    @Test
    void given_text_file_in_file_path__when_file_path_is_null__then_file_path_not_set_error() {

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobQueueRepositoryImplTest.getDerechoQstatJobsText();
        });

        assertEquals("File cannot be located: " + PATH_WITH_TRAILING_SLASH + DERECHO_FILE_NAME, exception.getMessage());
    }

    @Test
    void given_text_file_in_path__when_file_path_is_invalid__then_file_path_error() {

        JobQueueRepositoryImpl jobQueueRepository = new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter, "invalidPath");

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobQueueRepository.readFileWithPath(CASPER_FILE_NAME_TEXT);
        });

        assertEquals("File cannot be located: invalidPathcasper_qstat_jobs.txt", exception.getMessage());
    }

    @Test
    void when_read_text_file_with_path__if_file_exists__then_success() throws IOException {

        // Create temp file and write content
        Path tempFile = Files.createFile(tempDir.resolve(CASPER_FILE_NAME_TEXT));
        Files.writeString(tempFile, CASPER_FILE_NAME_TEXT);

        when(mockResourceLoader.getResource("file:" + PATH_WITH_TRAILING_SLASH + CASPER_FILE_NAME_TEXT)).thenReturn(mockResource);
       // when(mockResourceLoader.getResource(anyString())).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(true);
        when(mockResource.getURI()).thenReturn(tempFile.toUri());

        // Verify method output
        String result = jobQueueRepositoryImplTest.readFileWithPath(CASPER_FILE_NAME_TEXT);
        assertEquals(CASPER_FILE_NAME_TEXT, result);

        // Clean up temp file
        Files.deleteIfExists(tempFile);
    }

    @Test
    void when_read_file_with_path__if_file_does_not_exist__then_file_path_error() {

        JobQueueRepositoryImpl jobQueueRepository = new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter, PATH_WITH_TRAILING_SLASH);

        FileNotReadableException exception = assertThrows(FileNotReadableException.class, () -> {
            jobQueueRepository.readFileWithPath("nonexistent_file.txt");
        });

        assertEquals("File cannot be located: " + tempDir.toString() + "/nonexistent_file.txt", exception.getMessage());
    }

    @Test
    void given_validJson__when_getCasperQstatJobsJson__then_return_correct_object() throws FileNotReadableException, IOException {

        // setup
        String validJsonContent = """
    {
        "timestamp": 123456789,
        "pbs_version": "1.2",
        "pbs_server": "testServer",
        "Jobs": {}
    }
    """;
        JobData expectedJobData = new JobData(); // Mock output from JobDataJsonConverter
        expectedJobData.setTimestamp(123456789);
        expectedJobData.setPbsVersion("1.2");
        expectedJobData.setPbsServer("testServer");

        // Create a temporary file with the required content
        Path tempFile = Files.createTempFile(tempDir, "casper_qstat_jobs", ".json");
        Files.writeString(tempFile, validJsonContent);

        // Initialize repo
        //jobQueueRepositoryImplTest = new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter);

        // Spy on the real object to mock specific methods
        jobQueueRepositoryImplTest = Mockito.spy(new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter, PATH_WITH_TRAILING_SLASH));

        // Mock the behavior of the methods being called
        // when(jobQueueRepositoryImplTest.readFileWithPath(CASPER_FILE_NAME_JSON)).thenReturn(validJsonContent);

        // Mock 'readFileWithPath' to avoid actual file system access
        doReturn(validJsonContent).when(jobQueueRepositoryImplTest).readFileWithPath(CASPER_FILE_NAME_JSON);

        when(mockJobDataJsonConverter.convertJsonToJobData(validJsonContent)).thenReturn(expectedJobData);

        // The test
        JobData actualJobData = jobQueueRepositoryImplTest.getCasperQstatJobsJson();

        // Assert
        assertEquals(expectedJobData, actualJobData, "The returned JobData should match the expected data.");

        // Verify interactions
        verify(jobQueueRepositoryImplTest, times(1)).readFileWithPath(CASPER_FILE_NAME_JSON);
        verify(mockJobDataJsonConverter, times(1)).convertJsonToJobData(validJsonContent);

        // Clean up the file if needed
        tempFile.toFile().deleteOnExit();
    }

    @Test
    void given_validJson__when_getDerechoQstatJobsJson__then_return_correct_object() throws FileNotReadableException, IOException {

        // setup
        String validJsonContent = """
    {
        "timestamp": 123456789,
        "pbs_version": "1.2",
        "pbs_server": "testServer",
        "Jobs": {}
    }
    """;
        JobData expectedJobData = new JobData(); // Mock output from JobDataJsonConverter
        expectedJobData.setTimestamp(123456789);
        expectedJobData.setPbsVersion("1.2");
        expectedJobData.setPbsServer("testServer");

        // Create a temporary file with the required content
        Path tempFile = Files.createTempFile(tempDir, "derecho_qstat_jobs", ".json");
        Files.writeString(tempFile, validJsonContent);

        // Initialize repo
        //jobQueueRepositoryImplTest = new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter);

        // Spy on the real object to mock specific methods
        jobQueueRepositoryImplTest = Mockito.spy(new JobQueueRepositoryImpl(mockResourceLoader, mockJobDataJsonConverter, PATH_WITH_TRAILING_SLASH));

        // Mock the behavior of the methods being called
        // when(jobQueueRepositoryImplTest.readFileWithPath(CASPER_FILE_NAME_JSON)).thenReturn(validJsonContent);

        // Mock 'readFileWithPath' to avoid actual file system access
        doReturn(validJsonContent).when(jobQueueRepositoryImplTest).readFileWithPath(DERECHO_FILE_NAME_JSON);

        when(mockJobDataJsonConverter.convertJsonToJobData(validJsonContent)).thenReturn(expectedJobData);

        // The test
        JobData actualJobData = jobQueueRepositoryImplTest.getDerechoQstatJobsJson();

        // Assert
        assertEquals(expectedJobData, actualJobData, "The returned JobData should match the expected data.");

        // Verify interactions
        verify(jobQueueRepositoryImplTest, times(1)).readFileWithPath(DERECHO_FILE_NAME_JSON);
        verify(mockJobDataJsonConverter, times(1)).convertJsonToJobData(validJsonContent);

        // Clean up the file if needed
        tempFile.toFile().deleteOnExit();
    }


//    @Test
//    void testGetCasperQstatJobsJson_FileNotReadableException() throws FileNotReadableException {
//        // Mock the behavior to throw FileNotReadableException
//        when(jobQueueRepository.readFileWithPath(JobQueueRepositoryImpl.CASPER_QSTAT_JOBS_JSON))
//                .thenThrow(new FileNotReadableException("File not readable"));
//
//        // Assert the exception is thrown
//        assertThrows(FileNotReadableException.class, () -> jobQueueRepository.getCasperQstatJobsJson());
//
//        // Verify no interaction with JobDataJsonConverter
//        verify(jsonConverter, never()).convertJsonToJobData(anyString());
//    }

}