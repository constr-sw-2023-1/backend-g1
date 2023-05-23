package constsw.grupoum.courses.application.dto;

import java.util.Collection;

public record ErrorCauseDTO(String description, Collection<String> source) {

}
