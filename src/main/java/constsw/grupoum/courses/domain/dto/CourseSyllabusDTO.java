package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourseSyllabusDTO(@JsonProperty(required = true) String description,
        Collection<SylabusUnitDTO> units) {

}
