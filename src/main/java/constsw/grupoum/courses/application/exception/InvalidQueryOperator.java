package constsw.grupoum.courses.application.exception;

public class InvalidQueryOperator extends InvalidQueryException {

    public InvalidQueryOperator(String message) {
        super(message);
    }

    public InvalidQueryOperator(String message, Throwable cause) {
        super(message, cause);
    }

}
