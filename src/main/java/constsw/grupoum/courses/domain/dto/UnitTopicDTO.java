package constsw.grupoum.courses.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UnitTopicDTO(@JsonProperty(required = true) Integer number, @JsonProperty(required = true) String name) {

}
