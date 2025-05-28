package Exception;

public class CustomUncheckedException extends RuntimeException {
    // Constructor that accepts a message
    public CustomUncheckedException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public CustomUncheckedException(Throwable cause) {
        super(cause);
    }
}
