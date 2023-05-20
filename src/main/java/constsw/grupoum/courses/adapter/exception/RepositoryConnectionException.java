package constsw.grupoum.courses.adapter.exception;

public class RepositoryConnectionException extends RepositoryException {

    public RepositoryConnectionException(String message) {
        super(message);
    }

    public RepositoryConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryConnectionException(Throwable cause) {
        super(cause);
    }

}
