package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

public record SylabusUnitDTO(int number,
        String name,
        Collection<UnitTopicDTO> topics) {

}
