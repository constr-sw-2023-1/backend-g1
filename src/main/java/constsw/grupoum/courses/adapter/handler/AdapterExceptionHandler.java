package constsw.grupoum.courses.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.adapter.exception.AdapterException;
import constsw.grupoum.courses.adapter.exception.RepositoryException;
import constsw.grupoum.courses.adapter.exception.RepositoryConnectionException;
import constsw.grupoum.courses.adapter.exception.RepositoryDuplicateKeyException;
import constsw.grupoum.courses.application.dto.ErrorDTO;

@ControllerAdvice
public class AdapterExceptionHandler {

    @ExceptionHandler(AdapterException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(AdapterException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-510", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(RepositoryException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-511", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(RepositoryConnectionException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(RepositoryConnectionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-512", "Erro interno, favor contatar professor Arruda"));
    }

    @ExceptionHandler(RepositoryDuplicateKeyException.class)
    public ResponseEntity<ErrorDTO> handleThrowable(RepositoryDuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorDTO("CO-410", ex.getMessage()));
    }

}
