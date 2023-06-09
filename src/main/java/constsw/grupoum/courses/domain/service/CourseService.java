package constsw.grupoum.courses.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseUnitDTO;
import constsw.grupoum.courses.domain.dto.PatchCourseDTO;
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
        return courseMapper.toCourseDTOCollection(courseRepository.findAll().stream().filter(c -> c.getActive()).collect(Collectors.toList()));
    }

    public CourseDTO getById(UUID id) throws CourseException {
        return courseMapper.toCourseDTO(courseRepository.findById(id).orElse(null));
    }

    public void deleteById(UUID id) throws CourseException {
        try{
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Course not found"));
        course.setActive(false);
        courseRepository.save(course);
        } catch (NullPointerException e) {
            throw new NotNullException(e.getMessage(), e);
        }
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

    public CourseDTO patchCourse(UUID id, PatchCourseDTO course) throws CourseException {

        return courseMapper
                .toCourseDTO(courseRepository.patch(courseRepository
                        .findById(id)
                        .map(c -> {
                            return courseMapper.updateCourse(c,
                                    courseMapper.toCourseDTO(new PatchCourseDTO(course.name(), course.codcred(),
                                            course.workload(), course.objectives(),
                                            course.syllabus(), course.units(), validateBooks(course.bibliography()))));
                        })
                        .orElseThrow(
                                () -> new NotFoundEntityException(
                                        String.format("Course with id %s not found", id.toString())))));
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

        return booksRefs
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public BookRefDTO createBook(UUID id, BookRefDTO book) {

        Optional<Course> course = courseRepository.findById(id);

        patchCourse(id, course
                .map(c -> {

                    Collection<BookRefDTO> books = new ArrayList<>();
                    books.add(book);

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);
                    if (courseDTO.bibliography() != null)
                        books.addAll(courseDTO.bibliography());

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            courseDTO.units(),
                            books);

                }).orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s not found", id.toString()))));

        return bookRepository.findById(book.isbn13())
                .map(b -> bookMapper.toBookRefDTO(b))
                .orElseThrow(() -> new InvalidBookException(String.format("ISBN %s not found: ", book.isbn13())));
    }

    public void deleteBook(UUID id, String isbn13) {

        Optional<Course> course = courseRepository.findByIdAndBibliographyIsbn13(id, isbn13);

        patchCourse(id, course
                .map(c -> {

                    Collection<BookRefDTO> books = new ArrayList<>();

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);
                    if (courseDTO.bibliography() != null)
                        books.addAll(courseDTO.bibliography());

                    books.removeIf(book -> book.isbn13().equals(isbn13));

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            courseDTO.units(),
                            books);

                }).orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s not found", id.toString()))));

    }

    public Collection<BookRefDTO> findBibliography(UUID id) {
        return courseMapper.toCourseDTO(courseRepository.findById(id).orElse(null)).bibliography();
    }

    public Collection<CourseUnitDTO> findUnits(UUID id) {
        return courseMapper.toCourseUnitDTOCollection(courseRepository.findById(id)
                .map(course -> {
                    if (course != null && course.getUnits() != null)
                        return course.getUnits();
                    return null;
                })
                .orElse(null));
    }

    public CourseUnitDTO createCourseUnit(UUID id, CourseUnitDTO unit) {

        Optional<Course> course = courseRepository.findById(id);

        patchCourse(id, course
                .map(c -> {

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);

                    Collection<CourseUnitDTO> units = new ArrayList<>();
                    units.add(unit);

                    if (courseDTO == null)
                        throw new NotFoundEntityException(
                                String.format("Course %s is null", id.toString()));

                    if (courseDTO.units() != null)
                        units.addAll(courseDTO.units());

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            units,
                            courseDTO.bibliography());

                }).orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s not found", id.toString()))));

        return unit;
    }

    public void deleteCourseUnit(UUID id, int unitNumber) {

        Optional<Course> course = courseRepository.findByIdAndCourseUnitsNumber(id, unitNumber);

        patchCourse(id, course
                .map(c -> {

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);

                    if (courseDTO == null)
                        throw new NotFoundEntityException(
                                String.format("Course %s is null", id.toString()));

                    if (courseDTO.units() != null)
                        courseDTO.units()
                                .removeIf(unit -> unit.number().equals(unitNumber));

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            courseDTO.units(),
                            courseDTO.bibliography());

                }).orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s and unit %s not found", id.toString(), unitNumber))));

    }

    public CourseUnitDTO findUnit(UUID id, int numberUnit) {
        return courseMapper.toCourseUnitDTO(courseRepository.findByIdAndCourseUnitsNumber(id, numberUnit)
                .map(course -> course.getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .orElse(null))
                .orElse(null));
    }

    public UnitTopicDTO createUnitTopic(UUID id, int unitNumber, UnitTopicDTO topic) {

        Optional<Course> course = courseRepository.findByIdAndCourseUnitsNumber(id, unitNumber);

        patchCourse(id, course
                .map(c -> {

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);

                    Collection<UnitTopicDTO> topics = new ArrayList<>();
                    topics.add(topic);

                    Collection<CourseUnitDTO> units = courseMapper
                            .toCourseUnitDTOCollection(c.getUnits());

                            CourseUnitDTO actualUnit = units
                            .stream()
                            .filter(unit -> unit.number().equals(unitNumber))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundEntityException(
                                    String.format("Unit with number %s not found", unitNumber)));

                    units.remove(actualUnit);

                    Collection<UnitTopicDTO> actualTopics = actualUnit.topics();

                    if (actualTopics != null)
                        topics.addAll(actualTopics);

                    units.add(new CourseUnitDTO(actualUnit.number(), actualUnit.name(), topics));

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            units,
                            courseDTO.bibliography());

                }).orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s and unit number %s not found", id.toString(), unitNumber))));

        return topic;
    }

    public void deleteTopic(UUID id, int unitNumber, int topicNumber) {

        Optional<Course> course = courseRepository.findByIdAndCourseUnitsNumberAndCourseUnitsTopicsNumber(id,
                unitNumber, topicNumber);

        patchCourse(id, course
                .map(c -> {

                    CourseDTO courseDTO = courseMapper.toCourseDTO(c);

                    Collection<CourseUnitDTO> units = courseMapper
                            .toCourseUnitDTOCollection(c.getUnits());

                    CourseUnitDTO unit = units
                            .stream()
                            .filter(u -> u.number().equals(unitNumber))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundEntityException(
                                    String.format("Course with id %s and unit number %s", id.toString(), unitNumber)));

                    Collection<UnitTopicDTO> topics = unit.topics();

                    topics.removeIf(topic -> topic.number().equals(topicNumber));

                    units.add(new CourseUnitDTO(unit.number(), unit.name(), topics));

                    return new PatchCourseDTO(courseDTO.name(),
                            courseDTO.codcred(),
                            courseDTO.workload(),
                            courseDTO.objectives(),
                            courseDTO.syllabus(),
                            units,
                            courseDTO.bibliography());
                })
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("Course with id %s and unit number %s and topic number %s not found",
                                id.toString(), unitNumber, topicNumber))));

    }

    public Collection<UnitTopicDTO> findUnitTopics(UUID id, int numberUnit) {
        return courseMapper.toUnitTopicDTOCollection(courseRepository.findByIdAndCourseUnitsNumber(id, numberUnit)
                .map(course -> course.getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .orElse(null)
                        .getTopics())
                .orElse(null));
    }

    public UnitTopicDTO findUnitTopic(UUID id, int numberUnit, int numberTopic) {
        return courseMapper.toUnitTopicDTO(courseRepository
                .findByIdAndCourseUnitsNumberAndCourseUnitsTopicsNumber(id, numberUnit, numberTopic)
                .map(course -> course
                        .getUnits()
                        .stream()
                        .filter(unit -> unit.getNumber().equals(numberUnit))
                        .findFirst()
                        .map(unit -> unit.getTopics().stream().filter(topic -> topic.getNumber().equals(numberTopic))
                                .findFirst().orElse(null)))
                .orElse(Optional.empty()).orElse(null));
    }

}
