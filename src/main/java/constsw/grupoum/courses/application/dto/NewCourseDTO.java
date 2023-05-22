package constsw.grupoum.courses.application.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;

public record NewCourseDTO(@JsonProperty(required = true) String name,
        @JsonProperty(required = true) String codcred,
        Integer workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Collection<BookRefDTO> bibliography) {

}
