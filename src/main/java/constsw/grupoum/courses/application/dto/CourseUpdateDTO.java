package constsw.grupoum.courses.application.dto;

import java.util.Collection;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;

public record CourseUpdateDTO(
        String name,
        String codCred,
        int workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Collection<BookRefDTO> bibliography) {

}
