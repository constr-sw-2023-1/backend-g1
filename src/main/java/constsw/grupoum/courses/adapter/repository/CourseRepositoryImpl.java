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
    public void deleteById(UUID id) {
        courseRepositoryMongo.deleteById(id);
    }

    @Override
    public Course insert(Course course) {
        return courseRepositoryMongo.insert(course);
    }

    @Override
    public Course save(Course course) {
        return courseRepositoryMongo.save(course);
    }

    
}
