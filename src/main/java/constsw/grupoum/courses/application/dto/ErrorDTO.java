package constsw.grupoum.courses.application.dto;

import java.util.Collection;

public record ErrorDTO(String code, String description, Collection<ErrorCauseDTO> cause) {

}
