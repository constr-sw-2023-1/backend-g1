package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllTopicsUC {

    private final CourseService courseService;

    public Collection<UnitTopicDTO> run(UUID id, int unitNumber) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null) {
            SyllabusUnitDTO unit = course.syllabus()
                    .units()
                    .stream()
                    .filter(u -> u.number().equals(unitNumber))
                    .findFirst()
                    .orElse(null);
            if (unit != null)
                return unit.topics();
        }

        return null;
    }
}
