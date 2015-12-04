package api.error;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
public class RestException extends RuntimeException {
    private final String message;

    public RestException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
