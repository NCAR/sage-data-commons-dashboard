package edu.sage.datacommonsdashboard.repository;

import java.io.IOException;

public interface FileRepository {

    String readFileFromResources(String fileName) throws IOException;

    String readFileWithPath(String filePath) throws IOException;
}
