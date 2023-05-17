package constsw.grupoum.courses.domain.repository;

import java.util.Optional;
import java.util.UUID;

import constsw.grupoum.courses.domain.entity.Course;

public interface CourseRepository {

    Optional<Course> findById(UUID id);

    void deleteById(UUID id);

}
