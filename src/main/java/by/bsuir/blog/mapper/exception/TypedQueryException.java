package by.bsuir.blog.mapper.exception;

public class TypedQueryException
        extends QueryException {

    public TypedQueryException() {
    }

    public TypedQueryException(String message) {
        super(message);
    }

    public TypedQueryException(Throwable cause) {
        super(cause);
    }

    public TypedQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypedQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
