package by.bsuir.blog.service.exception;

public class CategoryServiceException
    extends ServiceException {

    public CategoryServiceException() {
    }

    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(Throwable cause) {
        super(cause);
    }

    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
