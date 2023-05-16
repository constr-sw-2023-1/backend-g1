package constsw.grupoum.courses.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.mapper.BookMapper;
import constsw.grupoum.courses.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookDTO create(BookDTO book) throws CourseException {
        try {
            return bookMapper.bookToBookDTO(bookRepository.insert(bookMapper.bookDTOToBook(book)));
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public Collection<BookDTO> getAll() throws CourseException {
        try {
            return bookMapper.collectionOfBookToCollectionOfBookDTO(bookRepository.findAll());
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public BookDTO getByIsbn13(String isbn13) throws CourseException {
        try {
            return bookMapper.bookToBookDTO(bookRepository.findById(isbn13).orElse(null));
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

}
