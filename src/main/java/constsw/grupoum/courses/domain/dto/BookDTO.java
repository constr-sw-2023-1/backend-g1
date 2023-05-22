package constsw.grupoum.courses.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDTO(@JsonProperty(required = true) String isbn13,
        @JsonProperty(required = true) String title,
        String author,
        Integer year,
        Integer pages,
        String language,
        String editor,
        String genre) {

}
