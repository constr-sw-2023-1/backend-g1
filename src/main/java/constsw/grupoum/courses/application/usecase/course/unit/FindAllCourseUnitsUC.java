package constsw.grupoum.courses.application.usecase.course.unit;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseUnitDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllCourseUnitsUC {

    private final CourseService courseService;

    public Collection<CourseUnitDTO> run(UUID id) {
        return courseService.findUnits(id);
    }

}
