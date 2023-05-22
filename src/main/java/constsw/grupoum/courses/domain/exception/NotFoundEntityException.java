package constsw.grupoum.courses.domain.exception;

public class NotFoundEntityException extends CourseException {

    public NotFoundEntityException(String message) {
        super(message);
    }

    public NotFoundEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
