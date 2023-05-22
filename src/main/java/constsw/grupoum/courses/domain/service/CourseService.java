package constsw.grupoum.courses.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;
import constsw.grupoum.courses.domain.mapper.BookMapper;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.repository.BookRepository;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public Collection<CourseDTO> getAll() throws CourseException {
        return courseMapper.toCourseDTOCollection(courseRepository.findAll());
    }

    public CourseDTO getById(UUID id) throws CourseException {
        return courseMapper.courseToCourseDTO(courseRepository.findById(id).orElse(null));
    }

    public void deleteById(UUID id) throws CourseException {
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) throws CourseException {

        validateBooks(courseDTO.bibliography());

        Course course = courseMapper.courseDTOToCourse(courseDTO);
        course.setId(id);
        course.setBibliography(bookMapper.toBookRefCollection(validateBooks(courseDTO.bibliography())));

        return courseMapper.courseToCourseDTO(courseRepository.save(course));
    }

    public CourseDTO createCourse(CourseDTO course) throws CourseException {

        Course courseEntity = courseMapper.courseDTOWithoutIdToCourseWithId(course);
        courseEntity.setBibliography(bookMapper.toBookRefCollection(validateBooks(course.bibliography())));

        return courseMapper
                .courseToCourseDTO(courseRepository.insert(courseEntity));
    }

    public Collection<CourseDTO> getByComplexQuery(Collection<QueryParam> queries) throws CourseException {
        try {
            return courseMapper.toCourseDTOCollection(courseRepository.findByComplexQuery(queries));
        } catch (CourseException e) {
            throw e;
        } catch (Throwable e) {
            throw new CourseException(e);
        }
    }

    private Collection<BookRefDTO> validateBooks(Collection<BookRefDTO> books) throws InvalidBookException {
        Collection<String> invalidISBNs = new ArrayList<>();
        Collection<BookRefDTO> booksRefs = new ArrayList<>();

        for (BookRefDTO book : books) {
            bookRepository.findById(book.isbn13())
                    .ifPresentOrElse(b -> booksRefs.add(bookMapper.toBookRefDTO(b)),
                            () -> invalidISBNs.add(book.isbn13()));
        }

        if (!invalidISBNs.isEmpty())
            throw new InvalidBookException("ISBNs not found: " + String.join(", ", invalidISBNs));

        return booksRefs;
    }

}
