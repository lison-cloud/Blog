package by.bsuir.blog.mapper.exception;

public class CriteriaUpdateException
        extends CriteriaException {

    public CriteriaUpdateException() {
    }

    public CriteriaUpdateException(String message) {
        super(message);
    }

    public CriteriaUpdateException(Throwable cause) {
        super(cause);
    }

    public CriteriaUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaUpdateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
