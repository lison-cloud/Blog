package by.bsuir.blog.mapper.exception;

public class QueryExecutorException
        extends Exception {

    public QueryExecutorException() {
    }

    public QueryExecutorException(String message) {
        super(message);
    }

    public QueryExecutorException(Throwable cause) {
        super(cause);
    }

    public QueryExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryExecutorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
