package by.bsuir.blog.repository.exception;

public class CategoryRepositoryException
    extends RepositoryException {

    public CategoryRepositoryException() {
    }

    public CategoryRepositoryException(String message) {
        super(message);
    }

    public CategoryRepositoryException(Throwable cause) {
        super(cause);
    }

    public CategoryRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
