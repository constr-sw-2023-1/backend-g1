package constsw.grupoum.courses.domain.dto;

import java.util.Collection;
import java.util.UUID;

public record CourseDTO(UUID id,
                String name,
                String codCred,
                int workload,
                Collection<String> objectives,
                CourseSyllabusDTO syllabus,
                Collection<BookRefDTO> bibliography) {

}
