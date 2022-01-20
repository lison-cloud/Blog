package by.bsuir.blog.repository.exception;

public class TagRepositoryException
    extends RepositoryException {

    public TagRepositoryException() {
    }

    public TagRepositoryException(String message) {
        super(message);
    }

    public TagRepositoryException(Throwable cause) {
        super(cause);
    }

    public TagRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
