package constsw.grupoum.courses.application.exception;

public abstract class ApplicationException extends Throwable {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}
