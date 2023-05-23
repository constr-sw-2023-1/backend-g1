package constsw.grupoum.courses.adapter.handler;

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

    private final ErrorUtils errorUtils;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleThrowable(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("CO-500",
                        "Erro interno, favor contatar professor Arruda",
                        errorUtils.toErrorStack(ex)));
    }

}
