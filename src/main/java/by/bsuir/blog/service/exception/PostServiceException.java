package by.bsuir.blog.service.exception;

public class PostServiceException
    extends ServiceException {

    public PostServiceException() {
    }

    public PostServiceException(String message) {
        super(message);
    }

    public PostServiceException(Throwable cause) {
        super(cause);
    }

    public PostServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
