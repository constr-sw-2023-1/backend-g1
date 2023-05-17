package constsw.grupoum.courses.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseUpdateDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateCourseUC {
    
    private final CourseService courseService;

    public CourseDTO run(UUID id ,CourseUpdateDTO courseDTO) throws CourseException {
        return courseService.updateCourse(id ,courseDTO);
    }
}
