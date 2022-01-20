package by.bsuir.blog.mapper.exception;

public class CriteriaBuilderException
        extends RuntimeException {

    public CriteriaBuilderException() {
    }

    public CriteriaBuilderException(String message) {
        super(message);
    }

    public CriteriaBuilderException(Throwable cause) {
        super(cause);
    }

    public CriteriaBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaBuilderException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
