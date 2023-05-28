package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateCourseUnitUC {

    private final CourseService courseService;

    public SyllabusUnitDTO run(UUID id, SyllabusUnitDTO unit) {
        return courseService.createSyllabusUnit(id, unit);
    }

}
