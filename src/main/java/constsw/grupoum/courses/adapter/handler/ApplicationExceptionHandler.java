package constsw.grupoum.courses.adapter.handler;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.adapter.util.ErrorUtils;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.exception.InvalidQueryException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class ApplicationExceptionHandler {

    @Value("${spring.application.name}")
    private String source;

    private final ErrorUtils errorUtils;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-520",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(InvalidQueryException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(InvalidQueryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Arrays.asList(new ErrorDTO("CO-320", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

}
