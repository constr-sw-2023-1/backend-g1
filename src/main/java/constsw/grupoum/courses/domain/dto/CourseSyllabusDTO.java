package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record CourseSyllabusDTO(@JsonProperty(required = true) String description,
        Collection<SyllabusUnitDTO> units) {

}
