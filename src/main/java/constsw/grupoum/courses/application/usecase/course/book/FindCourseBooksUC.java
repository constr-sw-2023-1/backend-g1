package constsw.grupoum.courses.application.usecase.course.book;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseBooksUC {

    private final CourseService courseService;

    public Collection<BookRefDTO> run(UUID id) throws CourseException {
        CourseDTO course = courseService.getById(id);
        return course == null ? null : course.bibliography();
    }

}
