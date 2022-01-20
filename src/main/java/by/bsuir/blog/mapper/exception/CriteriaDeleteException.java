package by.bsuir.blog.mapper.exception;

public class CriteriaDeleteException
        extends CriteriaException {

    public CriteriaDeleteException() {
    }

    public CriteriaDeleteException(String message) {
        super(message);
    }

    public CriteriaDeleteException(Throwable cause) {
        super(cause);
    }

    public CriteriaDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaDeleteException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
