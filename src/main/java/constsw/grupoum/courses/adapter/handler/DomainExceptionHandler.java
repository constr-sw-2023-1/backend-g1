package constsw.grupoum.courses.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.adapter.util.ErrorUtils;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidAtributeException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;
import constsw.grupoum.courses.domain.exception.NotFoundEntityException;
import constsw.grupoum.courses.domain.exception.NotNullException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class DomainExceptionHandler {

    private final ErrorUtils errorUtils;

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(CourseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-530",
                        "Erro interno, favor contatar professor Arruda",
                        errorUtils.toErrorStack(ex)));
    }

    @ExceptionHandler(InvalidAtributeException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidAtributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-331", ex.getMessage(), errorUtils.toErrorStack(ex)));
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidBookException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-332", ex.getMessage(), errorUtils.toErrorStack(ex)));
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(NotNullException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-334", ex.getMessage(), errorUtils.toErrorStack(ex)));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(NotFoundEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("CO-231", ex.getMessage(), errorUtils.toErrorStack(ex)));
    }

}
