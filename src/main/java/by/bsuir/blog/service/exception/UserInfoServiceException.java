package by.bsuir.blog.service.exception;

public class UserInfoServiceException
        extends ServiceException {

    public UserInfoServiceException() {
    }

    public UserInfoServiceException(String message) {
        super(message);
    }

    public UserInfoServiceException(Throwable cause) {
        super(cause);
    }

    public UserInfoServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
