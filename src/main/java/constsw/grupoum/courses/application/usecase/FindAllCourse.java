package constsw.grupoum.courses.application.usecase;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllCourse {

    private final CourseService courseService;

    public List<CourseDTO> run() throws CourseException {
        return courseService.getAll();
    }
}
