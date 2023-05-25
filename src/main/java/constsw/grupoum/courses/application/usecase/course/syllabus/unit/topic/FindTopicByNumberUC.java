package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.SylabusUnitDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindTopicByNumberUC {

    private final CourseService courseService;

    public UnitTopicDTO run(UUID id, int unitNumber, int topicNumber) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null) {
            SylabusUnitDTO unit = course.syllabus() == null ? null
                    : course.syllabus()
                            .units()
                            .stream()
                            .filter(u -> u.number().equals(unitNumber))
                            .findFirst()
                            .orElse(null);
            if (unit != null && unit.topics() != null) {
                return unit.topics()
                        .stream()
                        .filter(t -> t.number().equals(topicNumber))
                        .findFirst()
                        .orElse(null);
            }
        }

        return null;
    }

}
