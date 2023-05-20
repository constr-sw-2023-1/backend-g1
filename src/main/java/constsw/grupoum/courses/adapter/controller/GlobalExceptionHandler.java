package constsw.grupoum.courses.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.exception.InvalidQueryException;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidAtributeException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleThrowable(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(InvalidQueryException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidQueryException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", ex.getMessage()));
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(CourseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(InvalidAtributeException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(InvalidAtributeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", ex.getMessage()));
    }

}
