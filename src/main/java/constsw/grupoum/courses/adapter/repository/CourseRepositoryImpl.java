package constsw.grupoum.courses.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.mapper.mongo.MongoEntitiesMapper;
import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryMongo courseRepositoryMongo;

    private final MongoEntitiesMapper mapper;

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepositoryMongo.findById(id).map(course -> mapper.toCourse(course));
    }

    @Override
    public void deleteById(UUID id) {
        courseRepositoryMongo.deleteById(id);
    }

    @Override
    public Course insert(Course course) {
        return mapper.toCourse(courseRepositoryMongo.insert(mapper.toMongoCourse(course)));
    }
}
