package edu.sage.datacommonsdashboard.exception;

public class DateParseException extends RuntimeException {

    /**
     * Default constructor with no arguments.
     */
    public DateParseException() {
        super();
    }

    /**
     * Constructor with a custom message.
     *
     * @param message The custom exception message.
     */
    public DateParseException(String message) {
        super(message);
    }

    /**
     * Constructor with a custom message and a cause.
     *
     * @param message The custom exception message.
     * @param cause The underlying cause of the exception.
     */
    public DateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with just a cause.
     *
     * @param cause The underlying cause of the exception.
     */
    public DateParseException(Throwable cause) {
        super(cause);
    }
}
