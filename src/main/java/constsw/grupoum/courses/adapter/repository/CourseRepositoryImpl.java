package constsw.grupoum.courses.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryMongo courseRepositoryMongo;

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepositoryMongo.findById(id);
    }

    @Override
    public void insert(Course course) {
        courseRepositoryMongo.insert(course);
    }
}
