package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllCourseUnitsUC {

    private final CourseService courseService;

    public Collection<SyllabusUnitDTO> run(UUID id) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null)
            return course.syllabus().units();

        return null;
    }

}
