package constsw.grupoum.courses.application.usecase;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateCourseUC {

    private final CourseService courseService;

    public CourseDTO run(CourseDTO course) throws CourseException {
        return courseService.createCourse(course);
    }
}
