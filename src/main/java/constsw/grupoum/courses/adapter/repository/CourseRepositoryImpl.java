package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.entity.mongo.CourseMongo;
import constsw.grupoum.courses.adapter.mapper.mongo.MongoEntitiesMapper;
import constsw.grupoum.courses.adapter.mapper.mongo.MongoQueryMapper;
import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryMongo courseRepositoryMongo;

    private final MongoTemplate mongoTemplate;

    private final MongoQueryMapper mapperQuery;

    private final MongoEntitiesMapper mapperEntity;

    @Override
    public Collection<Course> findAll() {
        return mapperEntity.toCourseCollection(courseRepositoryMongo.findAll());
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepositoryMongo.findById(id).map(course -> mapperEntity.toCourse(course));
    }

    @Override
    public void deleteById(UUID id) {
        courseRepositoryMongo.deleteById(id);
    }

    @Override
    public Course insert(Course course) {
        return mapperEntity.toCourse(courseRepositoryMongo.insert(mapperEntity.toMongoCourse(course)));
    }

    @Override
    public Course save(Course course) {
        return mapperEntity.toCourse(courseRepositoryMongo.save(mapperEntity.toMongoCourse(course)));
    }

    @Override
    public Collection<Course> findByComplexQuery(Collection<QueryParam> queries) throws CourseException {
        return mapperEntity.toCourseCollection(
                mongoTemplate.find(mapperQuery.toQuery(CourseMongo.class, queries), CourseMongo.class));
    }
}
