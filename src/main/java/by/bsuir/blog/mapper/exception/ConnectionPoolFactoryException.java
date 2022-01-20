package by.bsuir.blog.mapper.exception;

public class ConnectionPoolFactoryException
        extends Exception {

    public ConnectionPoolFactoryException() {
    }

    public ConnectionPoolFactoryException(String message) {
        super(message);
    }

    public ConnectionPoolFactoryException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolFactoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
