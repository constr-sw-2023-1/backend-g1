package constsw.grupoum.courses.adapter.handler;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import constsw.grupoum.courses.adapter.util.ErrorUtils;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class JwtExceptionHandler {

    @Value("${spring.application.name}")
    private String source;

    private final ErrorUtils errorUtils;

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Arrays.asList(new ErrorDTO("CO-411",
                        "Erro interno, vindo da autenticação do token verifique se o token está correto",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(InvalidBearerTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Arrays.asList(new ErrorDTO("CO-411",
                        "Erro interno, vindo da autenticação do token verifique se o token está correto",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

}
