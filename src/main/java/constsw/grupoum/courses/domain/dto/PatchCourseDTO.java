package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

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
