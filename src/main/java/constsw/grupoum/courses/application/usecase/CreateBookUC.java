package constsw.grupoum.courses.application.usecase;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.BookService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateBookUC {

    private final BookService bookService;

    public BookDTO run(BookDTO book) throws CourseException {
        return bookService.create(book);
    }

}
