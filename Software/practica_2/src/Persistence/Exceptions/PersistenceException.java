package Persistence.Exceptions;

/**
 * A custom exception class for handling persistence-related errors.
 */
public class PersistenceException extends Exception {

    /**
     * Constructs a PersistenceException with the specified error message and cause.
     *
     * @param message The error message.
     * @param exception   The exception.
     */
    public PersistenceException(String message, Exception exception) {
        super(message, exception);
    }
}
