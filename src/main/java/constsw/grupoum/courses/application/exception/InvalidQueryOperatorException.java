package constsw.grupoum.courses.application.exception;

public class InvalidQueryOperatorException extends InvalidQueryException {

    public InvalidQueryOperatorException(String message) {
        super(message);
    }

    public InvalidQueryOperatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
