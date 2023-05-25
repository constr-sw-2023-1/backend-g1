package constsw.grupoum.courses.application.usecase.course;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseByIdUC {

    private final CourseService courseService;

    public CourseDTO run(UUID id) throws CourseException {
        return courseService.getById(id);
    }

}
