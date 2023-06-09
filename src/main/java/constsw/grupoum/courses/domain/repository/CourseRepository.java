package constsw.grupoum.courses.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.vo.QueryParam;

public interface CourseRepository {

    Collection<Course> findAll();

    Optional<Course> findById(UUID id);

    void deleteById(UUID id);

    Course insert(Course course);

    Course save(Course course);

    Course patch(Course course);

    Collection<Course> findByComplexQuery(Collection<QueryParam> queries) throws CourseException;

    Optional<Course> findByIdAndBibliographyIsbn13(UUID id, String isbn13);

    Optional<Course> findByIdAndCourseUnitsNumber(UUID id, int unitNumber);

    Optional<Course> findByIdAndCourseUnitsNumberAndCourseUnitsTopicsNumber(UUID id, int unitNumber,
            int topicNumber);

}
