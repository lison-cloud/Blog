package by.bsuir.blog.mapper.exception;

public class CriteriaInsertException
        extends CriteriaException {

    public CriteriaInsertException() {
    }

    public CriteriaInsertException(String message) {
        super(message);
    }

    public CriteriaInsertException(Throwable cause) {
        super(cause);
    }

    public CriteriaInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaInsertException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
