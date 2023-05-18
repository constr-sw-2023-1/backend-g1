package constsw.grupoum.courses.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.vo.QueryParam;

public interface CourseRepository {

    Optional<Course> findById(UUID id);

    void deleteById(UUID id);

    Course insert(Course course);

    Collection<Course> findByComplexQuery(Collection<QueryParam> queries);

}
