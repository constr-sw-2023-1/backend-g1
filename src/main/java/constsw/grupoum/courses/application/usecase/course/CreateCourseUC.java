package constsw.grupoum.courses.application.usecase.course;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateCourseUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public CourseDTO run(NewCourseDTO course) throws CourseException {
        return courseService.createCourse(courseMapper.toCourseDTO(course));
    }
}
