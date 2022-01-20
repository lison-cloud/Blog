package by.bsuir.blog.mapper.exception;

public class EntityManagerException
        extends Exception {

    public EntityManagerException() {
    }

    public EntityManagerException(String message) {
        super(message);
    }

    public EntityManagerException(Throwable cause) {
        super(cause);
    }

    public EntityManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityManagerException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
