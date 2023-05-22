package constsw.grupoum.courses.application.dto;

import java.util.Collection;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;

public record NewCourseDTO(String name,
        String codcred,
        Integer workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Collection<BookRefDTO> bibliography) {

}
