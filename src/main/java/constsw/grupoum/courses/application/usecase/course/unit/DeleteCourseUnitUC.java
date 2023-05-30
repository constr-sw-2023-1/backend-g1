package constsw.grupoum.courses.application.usecase.course.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCourseUnitUC {

    private final CourseService courseService;

    public void run(UUID id, int number) {
        courseService.deleteCourseUnit(id, number);
    }
}
