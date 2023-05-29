package constsw.grupoum.courses.application.usecase.course;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatchCourseUC {

    private final CourseService courseService;

    public CourseDTO run(UUID id, PatchCourseDTO course) throws CourseException {
        return courseService.patchCourse(id, course);
    }
}
