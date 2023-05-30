package constsw.grupoum.courses.application.usecase.course.unit.topic;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindTopicUC {

    private final CourseService courseService;

    public UnitTopicDTO run(UUID id, int unitNumber, int topicNumber) {
        return courseService.findUnitTopic(id, unitNumber, topicNumber);
    }

}
