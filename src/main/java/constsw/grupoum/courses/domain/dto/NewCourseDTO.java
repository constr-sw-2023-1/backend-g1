package constsw.grupoum.courses.domain.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewCourseDTO(@JsonProperty(required = true) String name,
        @JsonProperty(required = true) String codcred,
        Integer workload,
        Collection<String> objectives,
        CourseSyllabusDTO syllabus,
        Collection<BookRefDTO> bibliography) {

}
