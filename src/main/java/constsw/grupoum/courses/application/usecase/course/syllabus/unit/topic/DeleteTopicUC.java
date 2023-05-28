package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteTopicUC {

        private final CourseService courseService;

        public void run(UUID id, int unitNumber, int topicNumber) {
                courseService.deleteTopic(id, unitNumber, topicNumber);
        }
}
