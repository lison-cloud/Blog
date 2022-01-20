package by.bsuir.blog.service.exception;

public class PostCommentServiceException
    extends ServiceException {

    public PostCommentServiceException() {
    }

    public PostCommentServiceException(String message) {
        super(message);
    }

    public PostCommentServiceException(Throwable cause) {
        super(cause);
    }

    public PostCommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCommentServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
