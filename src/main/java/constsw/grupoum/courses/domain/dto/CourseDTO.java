package constsw.grupoum.courses.domain.dto;

import java.util.Collection;
import java.util.UUID;

public record CourseDTO(UUID id,
        String name,
        String codcred,
        Integer workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Boolean active,
        Collection<BookRefDTO> bibliography) {

}
