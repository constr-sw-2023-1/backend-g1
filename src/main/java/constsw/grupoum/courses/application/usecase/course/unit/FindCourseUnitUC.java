package constsw.grupoum.courses.application.usecase.course.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseUnitUC {

    private final CourseService courseService;

    public CourseUnitDTO run(UUID id, int number) {
        return courseService.findUnit(id, number);
    }

}
