package constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateTopicUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public Collection<UnitTopicDTO> run(UUID id, int unitNumber, UnitTopicDTO topic) {
        CourseDTO course = courseService.getById(id);
        Collection<UnitTopicDTO> topics = Arrays.asList(topic);

        if (course != null && course.syllabus() != null && course.syllabus().units() != null) {
            SyllabusUnitDTO unit = course
                    .syllabus()
                    .units()
                    .stream()
                    .filter(u -> u.number().equals(unitNumber))
                    .findFirst()
                    .orElse(null);

            if (unit != null && unit.topics() != null)
                topics.addAll(unit.topics());
        }

        courseService.patchCourse(id,
                courseMapper.toCourseDTO(
                        PatchCourseDTO
                                .builder()
                                .syllabus(CourseSyllabusDTO
                                        .builder()
                                        .units(Arrays.asList(SyllabusUnitDTO
                                                .builder()
                                                .topics(topics)
                                                .build()))
                                        .build())
                                .build()));

        return topics;
    }

}
