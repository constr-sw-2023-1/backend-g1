package constsw.grupoum.courses.adapter.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.dto.ErrorCauseDTO;

@Component
public class ErrorUtils {

    public Collection<ErrorCauseDTO> toErrorStack(Throwable e) {
        return resursivePathing(new ArrayList<ErrorCauseDTO>(), e);
    }

    private Collection<ErrorCauseDTO> resursivePathing(Collection<ErrorCauseDTO> errorStack, Throwable e) {
        Throwable cause = e.getCause();
        errorStack.add(new ErrorCauseDTO(e.getLocalizedMessage(), Arrays.asList(e.getStackTrace())
                .stream()
                .map(element -> String.format("%s.%s", element.getClassName(), element.getMethodName()))
                .collect(Collectors.toList())));
        if (cause == null)
            return errorStack;
        else
            return resursivePathing(errorStack, cause);
    }

}
