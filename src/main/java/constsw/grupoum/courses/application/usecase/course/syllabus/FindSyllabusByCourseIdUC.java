package constsw.grupoum.courses.application.usecase.course.syllabus;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindSyllabusByCourseIdUC {

    private final CourseService courseService;

    public CourseSyllabusDTO run(UUID id) throws CourseException {
        CourseDTO course = courseService.getById(id);
        if (course != null)
            return course.syllabus();
        return null;
    }

}
