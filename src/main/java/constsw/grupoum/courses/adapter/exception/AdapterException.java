package constsw.grupoum.courses.adapter.exception;

public abstract class AdapterException extends RuntimeException {

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterException(Throwable cause) {
        super(cause);
    }

}
