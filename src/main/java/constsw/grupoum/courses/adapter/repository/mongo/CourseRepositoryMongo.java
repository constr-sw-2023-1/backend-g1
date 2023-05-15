package constsw.grupoum.courses.adapter.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.domain.entity.Course;

@Repository
public interface CourseRepositoryMongo extends MongoRepository<Course, UUID> {

}
