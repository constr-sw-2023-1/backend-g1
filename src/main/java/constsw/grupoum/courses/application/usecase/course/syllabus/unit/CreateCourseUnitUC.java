package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.Arrays;
import java.util.Collection;
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
public class CreateCourseUnitUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public Collection<SyllabusUnitDTO> run(UUID id, SyllabusUnitDTO unit) {
        CourseDTO course = courseService.getById(id);
        Collection<SyllabusUnitDTO> units = Arrays.asList(unit);

        if (course != null && course.syllabus() != null && course.syllabus().units() != null)
            units.addAll(units);

        return courseService.patchCourse(id,
                courseMapper.toCourseDTO(
                        PatchCourseDTO
                                .builder()
                                .syllabus(CourseSyllabusDTO
                                        .builder()
                                        .units(units)
                                        .build())
                                .build()))
                .syllabus()
                .units();
    }

}
