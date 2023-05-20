package constsw.grupoum.courses.domain.exception;

public class InvalidAtributeException extends CourseException {

    public InvalidAtributeException(String message) {
        super(message);
    }

    public InvalidAtributeException(String message, Throwable cause) {
        super(message, cause);
    }

}
