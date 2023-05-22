package constsw.grupoum.courses.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidAtributeException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;

@ControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(CourseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-530", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(InvalidAtributeException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidAtributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-331", ex.getMessage()));
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidBookException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-332", ex.getMessage()));
    }
}
