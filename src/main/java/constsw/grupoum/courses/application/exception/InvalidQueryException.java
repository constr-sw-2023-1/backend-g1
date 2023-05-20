package constsw.grupoum.courses.application.exception;

public class InvalidQueryException extends ApplicationException {

    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(String message, Throwable cause) {
        super(message, cause);
    }

}
