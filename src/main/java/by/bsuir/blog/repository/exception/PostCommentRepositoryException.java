package by.bsuir.blog.repository.exception;

public class PostCommentRepositoryException
    extends RepositoryException {

    public PostCommentRepositoryException() {
    }

    public PostCommentRepositoryException(String message) {
        super(message);
    }

    public PostCommentRepositoryException(Throwable cause) {
        super(cause);
    }

    public PostCommentRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCommentRepositoryException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
