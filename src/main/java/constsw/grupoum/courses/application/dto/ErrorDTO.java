package constsw.grupoum.courses.application.dto;

public record ErrorDTO(String code, String description, String source, ErrorCauseDTO cause) {

}
