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

import constsw.grupoum.courses.adapter.exception.AdapterException;
import constsw.grupoum.courses.adapter.exception.RepositoryConnectionException;
import constsw.grupoum.courses.adapter.exception.RepositoryDuplicateKeyException;
import constsw.grupoum.courses.adapter.exception.RepositoryException;
import constsw.grupoum.courses.adapter.util.ErrorUtils;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.exception.InvalidQueryException;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidAtributeException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;
import constsw.grupoum.courses.domain.exception.NotFoundEntityException;
import constsw.grupoum.courses.domain.exception.NotNullException;
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

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(AuthenticationException ex) {
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

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(CourseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Arrays.asList(new ErrorDTO("CO-530",
                        "Erro interno, favor contatar professor Arruda",
                        source,
                        errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(InvalidAtributeException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(InvalidAtributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Arrays.asList(new ErrorDTO("CO-331", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(InvalidBookException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Arrays.asList(new ErrorDTO("CO-332", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(NotNullException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Arrays.asList(new ErrorDTO("CO-334", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<Collection<ErrorDTO>> handleThrowable(NotFoundEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Arrays.asList(new ErrorDTO("CO-231", ex.getMessage(), source, errorUtils.toErrorStack(ex))));
    }

}
