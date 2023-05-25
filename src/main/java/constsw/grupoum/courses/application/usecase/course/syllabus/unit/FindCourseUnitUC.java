package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseUnitUC {

    private final CourseService courseService;

    public SyllabusUnitDTO run(UUID id, int number) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null && course.syllabus().units() != null)
            return course.syllabus()
                    .units()
                    .stream()
                    .filter(unit -> unit.number().equals(number))
                    .findFirst()
                    .orElse(null);

        return null;
    }

}