package constsw.grupoum.courses.domain.dto;

import java.util.Collection;
import java.util.UUID;

public record CourseDTO(UUID id,
        String name,
        String codcred,
        Integer workload,
        Collection<String> objectives,
        String syllabus,
        Collection<CourseUnitDTO> units,
        Collection<BookRefDTO> bibliography) {

}
