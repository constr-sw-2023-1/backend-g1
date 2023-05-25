package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SyllabusUnitDTO(@JsonProperty(required = true) Integer number,
        @JsonProperty(required = true) String name,
        Collection<UnitTopicDTO> topics) {

}
