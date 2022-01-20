package by.bsuir.blog.mapper.exception;

public class CriteriaException
        extends Exception {

    public CriteriaException() {
    }

    public CriteriaException(String message) {
        super(message);
    }

    public CriteriaException(Throwable cause) {
        super(cause);
    }

    public CriteriaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
