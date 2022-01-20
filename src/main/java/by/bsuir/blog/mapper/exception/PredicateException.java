package by.bsuir.blog.mapper.exception;

public class PredicateException
        extends Exception {

    public PredicateException() {
    }

    public PredicateException(String message) {
        super(message);
    }

    public PredicateException(Throwable cause) {
        super(cause);
    }

    public PredicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PredicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
