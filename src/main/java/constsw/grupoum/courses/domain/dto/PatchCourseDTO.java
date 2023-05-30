package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

import lombok.Builder;

@Builder
public record PatchCourseDTO(
        String name,
        String codcred,
        Integer workload,
        Collection<String> objectives,
        String syllabus,
        Collection<CourseUnitDTO> units,
        Collection<BookRefDTO> bibliography) {

}
