package constsw.grupoum.courses.domain.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.repository.BookRepository;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final BookRepository bookRepository;

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

        return courseMapper.courseToCourseDTO(courseRepository.save(course));
    }

    public CourseDTO createCourse(CourseDTO course) throws CourseException {

        validateBooks(course.bibliography());

        return courseMapper
                .courseToCourseDTO(courseRepository.insert(courseMapper.courseDTOWithoutIdToCourseWithId(course)));
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

    private void validateBooks(Collection<BookRefDTO> books) throws InvalidBookException {

        Boolean erro = false;
        StringBuilder sb = new StringBuilder();

        for (BookRefDTO book : books) {
            if (bookRepository.findById(book.isbn13()).isEmpty()) {
                erro = true;
                sb.append(",");
                sb.append(book.isbn13());
            }
        }

        if (erro)
            throw new InvalidBookException(String.format("IBNS's %s not found", sb.toString()));
    }

}
