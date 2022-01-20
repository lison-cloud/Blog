package by.bsuir.blog.mapper.exception;

public class CriteriaSelectException
        extends CriteriaException {

    public CriteriaSelectException() {
    }

    public CriteriaSelectException(String message) {
        super(message);
    }

    public CriteriaSelectException(Throwable cause) {
        super(cause);
    }

    public CriteriaSelectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaSelectException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
