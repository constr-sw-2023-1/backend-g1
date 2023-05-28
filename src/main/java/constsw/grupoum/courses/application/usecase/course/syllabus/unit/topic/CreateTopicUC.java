package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateTopicUC {

    private final CourseService courseService;

    public UnitTopicDTO run(UUID id, int unitNumber, UnitTopicDTO topic) {
        return courseService.createUnitTopic(id, unitNumber, topic);
    }

}
