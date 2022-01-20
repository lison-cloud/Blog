package by.bsuir.blog.repository.exception;

public class PostRepositoryException
    extends RepositoryException {

    public PostRepositoryException() {
    }

    public PostRepositoryException(String message) {
        super(message);
    }

    public PostRepositoryException(Throwable cause) {
        super(cause);
    }

    public PostRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
