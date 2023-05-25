package constsw.grupoum.courses.application.dto;

import java.util.Collection;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import lombok.Builder;

@Builder
public record PatchCourseDTO(
        String name,
        String codcred,
        Integer workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Collection<BookRefDTO> bibliography) {

}
