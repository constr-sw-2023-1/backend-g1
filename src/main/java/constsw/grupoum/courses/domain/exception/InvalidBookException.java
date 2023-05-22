package constsw.grupoum.courses.domain.exception;

public class InvalidBookException extends CourseException {

    public InvalidBookException(String message) {
        super(message);
    }

    public InvalidBookException(String message, Throwable cause) {
        super(message, cause);
    }

}
