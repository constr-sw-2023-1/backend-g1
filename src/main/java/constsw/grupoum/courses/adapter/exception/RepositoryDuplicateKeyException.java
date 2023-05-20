package constsw.grupoum.courses.adapter.exception;

public class RepositoryDuplicateKeyException extends RepositoryException {

    public RepositoryDuplicateKeyException(String message) {
        super(message);
    }

    public RepositoryDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryDuplicateKeyException(Throwable cause) {
        super(cause);
    }

}
