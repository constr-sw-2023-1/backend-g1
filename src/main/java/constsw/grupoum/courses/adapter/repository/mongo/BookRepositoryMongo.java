package constsw.grupoum.courses.adapter.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import constsw.grupoum.courses.adapter.entity.mongo.BookMongo;


public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {

}
