package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

public record CourseSyllabusDTO(String description,
        Collection<SylabusUnitDTO> units) {

}
