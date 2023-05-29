package constsw.grupoum.courses.application.usecase.course.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCourseBookByIsbnUC {

    private final CourseService courseService;

    public void run(UUID id, String isbn) throws CourseException {
        courseService.deleteBook(id, isbn);
    }

}
