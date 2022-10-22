package exception;

public class NonExistingEntityException extends RuntimeException {
    public NonExistingEntityException(final String message) {
        super(message);
    }

    public NonExistingEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
