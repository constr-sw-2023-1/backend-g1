package constsw.grupoum.courses.application.usecase.course.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateCourseBookUC {

    private final CourseService courseService;

    public BookRefDTO run(UUID id, BookRefDTO book) {
        return courseService.createBook(id, book);
    }

}
