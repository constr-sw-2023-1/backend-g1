package constsw.grupoum.courses.adapter.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import constsw.grupoum.courses.adapter.entity.mongo.Book;


public interface BookRepositoryMongo extends MongoRepository<Book, String> {

}
