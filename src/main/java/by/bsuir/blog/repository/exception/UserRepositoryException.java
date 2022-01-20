package by.bsuir.blog.repository.exception;

public class UserRepositoryException
    extends RepositoryException {

    public UserRepositoryException() {
    }

    public UserRepositoryException(String message) {
        super(message);
    }

    public UserRepositoryException(Throwable cause) {
        super(cause);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
