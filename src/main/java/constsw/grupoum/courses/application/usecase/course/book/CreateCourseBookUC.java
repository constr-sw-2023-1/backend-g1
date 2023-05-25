package constsw.grupoum.courses.application.usecase.course.book;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateCourseBookUC {

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public Collection<BookRefDTO> run(UUID id, BookRefDTO book) {
        CourseDTO course = courseService.getById(id);
        Collection<BookRefDTO> bibliography = Arrays.asList(book);

        if (course != null && course.bibliography() != null)
            bibliography.addAll(bibliography);

        return courseService.patchCourse(id,
                courseMapper.toCourseDTO(PatchCourseDTO.builder().bibliography(bibliography).build())).bibliography();
    }

}
