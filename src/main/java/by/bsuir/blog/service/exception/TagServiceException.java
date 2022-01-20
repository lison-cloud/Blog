package by.bsuir.blog.service.exception;

public class TagServiceException
    extends ServiceException {

    public TagServiceException() {
    }

    public TagServiceException(String message) {
        super(message);
    }

    public TagServiceException(Throwable cause) {
        super(cause);
    }

    public TagServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
