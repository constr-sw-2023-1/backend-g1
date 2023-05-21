package constsw.grupoum.courses.application.usecase;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.mapper.QueryMapper;
import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.BookService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindBooksByComplexQueryUC {

    private final BookService bookService;

    private final QueryMapper queryMapper;

    public Collection<BookDTO> run(Map<String, String> queryParams) throws CourseException, ApplicationException {
        return bookService.getByComplexQuery(queryMapper.mapToCollectionOfQueryParam(queryParams));
    }
}