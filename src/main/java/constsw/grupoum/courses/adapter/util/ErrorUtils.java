package constsw.grupoum.courses.adapter.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.dto.ErrorCauseDTO;

@Component
public class ErrorUtils {

    public ErrorCauseDTO toErrorStack(Throwable e) {
        return new ErrorCauseDTO(e.getLocalizedMessage(), Arrays.asList(e.getStackTrace())
                .stream()
                .filter(element -> element.getClassName().startsWith("constsw.grupoum"))
                .filter(element -> element.getLineNumber() > 0)
                .map(element -> String.format("%s.%s line:%s", element.getClassName(), element.getMethodName(),
                        element.getLineNumber()))
                .collect(Collectors.toList()));
    }

}
