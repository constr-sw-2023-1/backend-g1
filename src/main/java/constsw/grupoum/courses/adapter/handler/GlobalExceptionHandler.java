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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String source;

    private final ErrorUtils errorUtils;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-500",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

}
