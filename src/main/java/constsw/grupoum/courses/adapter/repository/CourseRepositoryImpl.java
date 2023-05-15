package constsw.grupoum.courses.adapter.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.repository.jpa.CourseRepositoryJpa;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryJpa courseRepositoryJpa;

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepositoryJpa.findById(id);
    }

    @Override
    public List<Course> findAll() { return courseRepositoryJpa.findAll(); }

    @Override
    public void createCourse(Course course) {
    }
}
