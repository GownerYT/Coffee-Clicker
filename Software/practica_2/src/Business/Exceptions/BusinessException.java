package Business.Exceptions;

/**
 * A custom exception class for handling business-related errors.
 */
public class BusinessException extends Exception {

    /**
     * Constructs a BusinessException with the specified error message and cause.
     *
     * @param message The error message.
     * @param exception   The exception.
     */
    public BusinessException(String message, Exception exception) {
        super(message, exception);
    }
}
