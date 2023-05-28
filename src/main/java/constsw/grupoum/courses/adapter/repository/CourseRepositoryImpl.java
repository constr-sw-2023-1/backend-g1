package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.entity.mongo.CourseMongo;
import constsw.grupoum.courses.adapter.exception.RepositoryConnectionException;
import constsw.grupoum.courses.adapter.exception.RepositoryDuplicateKeyException;
import constsw.grupoum.courses.adapter.mapper.mongo.MongoEntitiesMapper;
import constsw.grupoum.courses.adapter.mapper.mongo.MongoQueryMapper;
import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.exception.InvalidAtributeException;
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
        try {
            return mapperEntity.toCourseCollection(courseRepositoryMongo.findAll());
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Optional<Course> findById(UUID id) {
        try {
            return courseRepositoryMongo.findById(id).map(course -> mapperEntity.toCourse(course));
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            courseRepositoryMongo.deleteById(id);
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Course insert(Course course) {
        try {
            return mapperEntity.toCourse(courseRepositoryMongo.insert(mapperEntity.toMongoCourse(course)));
        } catch (DuplicateKeyException e) {
            throw new RepositoryDuplicateKeyException(String.format("%s already registered", course.getId()), e);
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Course save(Course course) {
        try {
            return mapperEntity.toCourse(courseRepositoryMongo.save(mapperEntity.toMongoCourse(course)));
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Collection<Course> findByComplexQuery(Collection<QueryParam> queries) throws CourseException {
        try {
            return mapperEntity.toCourseCollection(
                    mongoTemplate.find(mapperQuery.toQuery(CourseMongo.class, queries), CourseMongo.class));
        } catch (InvalidAtributeException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Course patch(Course course) {
        try {
            return mapperEntity.toCourse(courseRepositoryMongo.save(mapperEntity.toMongoCourse(course)));
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Optional<Course> findByIdAndSyllabusUnitsNumberAndSyllabusUnitsTopicsNumber(UUID id, int unitNumber,
            int topicNumber) {
        try {
            return courseRepositoryMongo
                    .findByIdAndSyllabusUnitsNumberAndSyllabusUnitsTopicsNumber(id, unitNumber, topicNumber)
                    .map(unit -> mapperEntity.toCourse(unit));
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }
}
