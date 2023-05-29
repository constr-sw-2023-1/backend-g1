package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseUnitUC {

    private final CourseService courseService;

    public SyllabusUnitDTO run(UUID id, int number) {
        return courseService.findUnit(id, number);
    }

}
