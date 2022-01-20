package by.bsuir.blog.repository.exception;

public class UserRoleRepositoryException
    extends RepositoryException {

    public UserRoleRepositoryException() {
    }

    public UserRoleRepositoryException(String message) {
        super(message);
    }

    public UserRoleRepositoryException(Throwable cause) {
        super(cause);
    }

    public UserRoleRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
