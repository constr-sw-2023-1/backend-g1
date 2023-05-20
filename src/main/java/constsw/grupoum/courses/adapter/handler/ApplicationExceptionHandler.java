package constsw.grupoum.courses.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.exception.InvalidQueryException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-520", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(InvalidQueryException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidQueryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("CO-320", ex.getMessage()));
    }

}
