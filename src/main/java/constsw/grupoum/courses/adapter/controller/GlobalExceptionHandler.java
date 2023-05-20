package constsw.grupoum.courses.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.domain.exception.CourseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(CourseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-501", "Erro interno, favor contatar professor Arruda"));
    }

}
