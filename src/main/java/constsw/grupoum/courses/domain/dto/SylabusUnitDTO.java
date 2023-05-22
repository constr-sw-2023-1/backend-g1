package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

public record SylabusUnitDTO(Integer number,
        String name,
        Collection<UnitTopicDTO> topics) {

}
