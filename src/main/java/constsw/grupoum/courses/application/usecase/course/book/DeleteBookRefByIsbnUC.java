package constsw.grupoum.courses.application.usecase.course.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBookRefByIsbnUC {

    private final CourseService courseService;

    public void run(UUID id, String isbn) throws CourseException {
        CourseDTO course = courseService.getById(id);
        if (course != null && course.bibliography() != null)
            course.bibliography().removeIf(book -> book.isbn13().equals(isbn));

        courseService.patchCourse(id, course);
    }

}
