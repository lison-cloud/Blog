package by.bsuir.blog.mapper.exception;

public class NoSuchColumnException
        extends RuntimeException {

    public NoSuchColumnException() {
    }

    public NoSuchColumnException(String message) {
        super(message);
    }

    public NoSuchColumnException(Throwable cause) {
        super(cause);
    }

    public NoSuchColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchColumnException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
