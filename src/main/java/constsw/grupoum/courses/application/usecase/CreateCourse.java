package constsw.grupoum.courses.application.usecase;

import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCourse {

    private final CourseService courseService;

    public void run(Course course) throws CourseException {
        courseService.createCourse(course);
    }
}
