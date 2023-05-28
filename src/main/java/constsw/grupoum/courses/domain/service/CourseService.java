package constsw.grupoum.courses.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseSyllabusDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidBookException;
import constsw.grupoum.courses.domain.exception.NotFoundEntityException;
import constsw.grupoum.courses.domain.exception.NotNullException;
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
        return courseMapper.toCourseDTO(courseRepository.findById(id).orElse(null));
    }

    public void deleteById(UUID id) throws CourseException {
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) throws CourseException {
        try {
            Course course = courseMapper.toCourse(courseDTO);
            course.setId(id);
            course.setBibliography(bookMapper.toBookRefCollection(validateBooks(courseDTO.bibliography())));

            return courseMapper.toCourseDTO(courseRepository.save(course));
        } catch (NullPointerException e) {
            throw new NotNullException(e.getMessage(), e);
        }
    }

    public CourseDTO createCourse(CourseDTO course) throws CourseException {
        try {
            Course courseEntity = courseMapper.toCourseWithId(course);
            courseEntity.setBibliography(bookMapper.toBookRefCollection(validateBooks(course.bibliography())));

            return courseMapper
                    .toCourseDTO(courseRepository.insert(courseEntity));
        } catch (NullPointerException e) {
            throw new NotNullException(e.getMessage(), e);
        }
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

    public CourseDTO patchCourse(UUID id, CourseDTO course) throws CourseException {

        validateBooks(course.bibliography());

        return courseMapper
                .toCourseDTO(courseRepository.patch(courseMapper.updateCourse(courseRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new NotFoundEntityException(
                                        String.format("Course with id %s not found", id.toString()))),
                        course)));
    }

    private Collection<BookRefDTO> validateBooks(Collection<BookRefDTO> books) throws InvalidBookException {
        Collection<String> invalidISBNs = new ArrayList<>();
        Collection<BookRefDTO> booksRefs = new ArrayList<>();

        if (books == null)
            return null;

        for (BookRefDTO book : books) {
            bookRepository.findById(book.isbn13())
                    .ifPresentOrElse(b -> booksRefs.add(bookMapper.toBookRefDTO(b)),
                            () -> invalidISBNs.add(book.isbn13()));
        }

        if (!invalidISBNs.isEmpty())
            throw new InvalidBookException("ISBNs not found: " + String.join(", ", invalidISBNs));

        return booksRefs;
    }

    public Collection<BookRefDTO> findBibliography(UUID id) {
        return courseMapper.toCourseDTO(courseRepository.findById(id).orElse(null)).bibliography();
    }

    public CourseSyllabusDTO findSyllabus(UUID id) {
        return courseMapper.toCourseDTO(courseRepository.findById(id).orElse(null)).syllabus();
    }

    public Collection<SyllabusUnitDTO> findUnits(UUID id) {
        return courseMapper.toSyllabusUnitDTOCollection(courseRepository.findById(id)
                .map(course -> {
                    if (course.getSyllabus() != null && course.getSyllabus().getUnits() != null)
                        return course.getSyllabus().getUnits();
                    return null;
                })
                .orElse(null));
    }

    public SyllabusUnitDTO findUnit(UUID id, int numberUnit) {
        return courseMapper.toSyllabusUnitDTO(courseRepository.findByIdAndSyllabusUnitsNumber(id, numberUnit)
                .map(course -> course.getSyllabus()
                        .getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .orElse(null))
                .orElse(null));
    }

    public Collection<UnitTopicDTO> findUnitTopics(UUID id, int numberUnit) {
        return courseMapper.toUnitTopicDTOCollection(courseRepository.findByIdAndSyllabusUnitsNumber(id, numberUnit)
                .map(course -> course.getSyllabus()
                        .getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .orElse(null)
                        .getTopics())
                .orElse(null));
    }

    public UnitTopicDTO findUnitTopic(UUID id, int numberUnit, int numberTopic) {
        return courseMapper.toUnitTopicDTO(courseRepository
                .findByIdAndSyllabusUnitsNumberAndSyllabusUnitsTopicsNumber(id, numberUnit, numberTopic)
                .map(course -> course
                        .getSyllabus()
                        .getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .map(unit -> unit.getTopics().stream().filter(topic -> topic.getNumber().equals(numberTopic))
                                .findFirst().orElse(null)))
                .orElse(Optional.empty()).orElse(null));
    }

}
