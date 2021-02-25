package spring.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(final String message) {
        super(message);
    }
}
