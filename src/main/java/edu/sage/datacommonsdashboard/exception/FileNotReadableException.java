package edu.sage.datacommonsdashboard.exception;

public class FileNotReadableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotReadableException(String message) {
        super(message);
    }

    public FileNotReadableException(String message, Throwable cause) {
        super(message, cause);
    }
}
