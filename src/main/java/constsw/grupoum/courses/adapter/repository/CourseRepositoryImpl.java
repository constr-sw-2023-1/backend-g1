package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.mapper.mongo.MongoEntitiesMapper;
import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryMongo courseRepositoryMongo;

    private final MongoEntitiesMapper mapper;

    @Override
    public Collection<Course> findAll() {
        return mapper.toCourseCollection(courseRepositoryMongo.findAll());
    }

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

    @Override
    public Course save(Course course) {
        return mapper.toCourse(courseRepositoryMongo.save(mapper.toMongoCourse(course)));
    }


    @Override
    public Collection<Course> findByComplexQuery(Collection<QueryParam> queries) {

    
        

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByComplexQueries'");
    }
}
