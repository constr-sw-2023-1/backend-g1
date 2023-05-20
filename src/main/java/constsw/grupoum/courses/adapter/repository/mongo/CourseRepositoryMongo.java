package constsw.grupoum.courses.adapter.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.entity.mongo.CourseMongo;

@Repository
public interface CourseRepositoryMongo extends MongoRepository<CourseMongo, UUID> {

}
