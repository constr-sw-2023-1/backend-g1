package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllTopicsUC {

    private final CourseService courseService;

    public Collection<UnitTopicDTO> run(UUID id, int unitNumber) {
        return courseService.findUnitTopics(id, unitNumber);
    }
}
