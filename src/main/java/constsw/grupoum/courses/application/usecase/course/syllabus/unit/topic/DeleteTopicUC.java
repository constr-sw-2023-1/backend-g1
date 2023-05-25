package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteTopicUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public void run(UUID id, int unitNumber, int topicNumber) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null && course.syllabus().units() != null) {
            SyllabusUnitDTO unit = course
                    .syllabus()
                    .units()
                    .stream()
                    .filter(u -> u.number().equals(unitNumber))
                    .findFirst()
                    .orElse(null);

            if (unit != null && unit.topics() != null) {
                unit.topics().removeIf(t -> t.number().equals(topicNumber));
                courseService.patchCourse(id,
                        courseMapper.toCourseDTO(
                                PatchCourseDTO
                                        .builder()
                                        .syllabus(CourseSyllabusDTO
                                                .builder()
                                                .units(Arrays.asList(SyllabusUnitDTO
                                                        .builder()
                                                        .topics(unit.topics())
                                                        .build()))
                                                .build())
                                        .build()));
            }
        }

    }
}
