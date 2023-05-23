package constsw.grupoum.courses.adapter.handler;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.adapter.exception.AdapterException;
import constsw.grupoum.courses.adapter.exception.RepositoryException;
import constsw.grupoum.courses.adapter.util.ErrorUtils;
import constsw.grupoum.courses.adapter.exception.RepositoryConnectionException;
import constsw.grupoum.courses.adapter.exception.RepositoryDuplicateKeyException;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class AdapterExceptionHandler {

    @Value("${spring.application.name}")
    private String source;

    private final ErrorUtils errorUtils;

    @ExceptionHandler(AdapterException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(AdapterException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-510",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(RepositoryException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-511",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(RepositoryConnectionException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(RepositoryConnectionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-512",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(RepositoryDuplicateKeyException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(RepositoryDuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Arrays.asList(new ErrorDTO("CO-410", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

}
