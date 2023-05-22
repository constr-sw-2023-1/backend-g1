package constsw.grupoum.courses.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookRefDTO(@JsonProperty(required = true) String isbn13, String title) {

}
