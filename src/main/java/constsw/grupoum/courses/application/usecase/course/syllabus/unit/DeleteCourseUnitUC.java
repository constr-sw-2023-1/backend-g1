package constsw.grupoum.courses.application.usecase.course.syllabus.unit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCourseUnitUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public void run(UUID id, int number) {
        CourseDTO course = courseService.getById(id);

        if (course != null && course.syllabus() != null && course.syllabus().units() != null) {
            course.syllabus().units().removeIf(unit -> unit.number().equals(number));
            courseService.patchCourse(id,
                    courseMapper.toCourseDTO(PatchCourseDTO
                            .builder()
                            .syllabus(CourseSyllabusDTO
                                    .builder()
                                    .units(course.syllabus().units())
                                    .build())
                            .build()));
        }
    }
}
