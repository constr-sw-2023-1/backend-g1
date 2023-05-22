package constsw.grupoum.courses.domain.exception;

public class NotNullException extends CourseException {

    public NotNullException(String message) {
        super(message);
    }

    public NotNullException(String message, Throwable cause) {
        super(message, cause);
    }

}