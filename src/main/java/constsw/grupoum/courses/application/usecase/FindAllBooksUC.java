package constsw.grupoum.courses.application.usecase;

import java.util.Collection;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.BookService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindAllBooksUC {

    private final BookService bookService;

    public Collection<BookDTO> run() throws CourseException {
        return bookService.getAll();
    }

}
