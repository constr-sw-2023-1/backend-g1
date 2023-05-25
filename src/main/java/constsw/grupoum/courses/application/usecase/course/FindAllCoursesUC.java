package constsw.grupoum.courses.application.usecase.course;

import java.util.Collection;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllCoursesUC {

    private final CourseService courseService;

    public Collection<CourseDTO> run() throws CourseException {
        return courseService.getAll();
    }

}
