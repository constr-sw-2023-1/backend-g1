package constsw.grupoum.courses.adapter.mapper.mongo;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.adapter.entity.mongo.BookMongo;
import constsw.grupoum.courses.adapter.entity.mongo.BookRefMongo;
import constsw.grupoum.courses.adapter.entity.mongo.CourseMongo;
import constsw.grupoum.courses.adapter.entity.mongo.CourseUnitMongo;
import constsw.grupoum.courses.adapter.entity.mongo.UnitTopicMongo;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.entity.CourseUnit;
import constsw.grupoum.courses.domain.entity.UnitTopic;

@Component
@Mapper(componentModel = "spring")
public interface MongoEntitiesMapper {

    MongoEntitiesMapper INSTANCE = Mappers.getMapper(MongoEntitiesMapper.class);

    BookMongo toMongoBook(Book book);

    Book toBook(BookMongo book);

    Collection<Book> toBookCollection(Collection<BookMongo> books);

    BookRefMongo toMongoBookRef(BookRef bookRef);

    BookRef toBookRef(BookRefMongo bookRef);

    CourseMongo toMongoCourse(Course course);

    Course toCourse(CourseMongo course);

    Collection<Course> toCourseCollection(Collection<CourseMongo> course);

    CourseUnitMongo toMongoCourseUnit(CourseUnit courseUnit);

    CourseUnit toCourseUnit(CourseUnitMongo courseUnit);

    UnitTopicMongo toMongoUnitTopic(UnitTopic unitTopic);

    UnitTopic toUnitTopic(UnitTopicMongo unitTopic);

}
