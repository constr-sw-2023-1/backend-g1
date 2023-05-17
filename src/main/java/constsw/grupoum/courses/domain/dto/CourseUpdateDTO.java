package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

public record CourseUpdateDTO(
                String name,
                String codCred,
                int workload,
                Collection<String> objectives,
                CourseSyllabusDTO syllabus,
                Collection<BookRefDTO> bibliography) {

}
