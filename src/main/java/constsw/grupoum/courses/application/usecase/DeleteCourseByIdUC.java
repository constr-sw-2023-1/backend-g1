package constsw.grupoum.courses.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCourseByIdUC {

    private final CourseService courseService;

    public void run(UUID id) throws CourseException {
        courseService.deleteById(id);
    }

}
