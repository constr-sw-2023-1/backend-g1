package constsw.grupoum.courses.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import constsw.grupoum.courses.domain.entity.Course;

public interface CourseRepository {

    Optional<Course> findById(UUID id);
    List<Course> findAll();
}
