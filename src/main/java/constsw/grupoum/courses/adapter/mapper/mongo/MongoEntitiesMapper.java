package constsw.grupoum.courses.adapter.mapper.mongo;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.adapter.entity.mongo.BookMongo;
import constsw.grupoum.courses.adapter.entity.mongo.BookRefMongo;
import constsw.grupoum.courses.adapter.entity.mongo.CourseMongo;
import constsw.grupoum.courses.adapter.entity.mongo.CourseSyllabusMongo;
import constsw.grupoum.courses.adapter.entity.mongo.SyllabusUnitMongo;
import constsw.grupoum.courses.adapter.entity.mongo.UnitTopicMongo;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.entity.CourseSyllabus;
import constsw.grupoum.courses.domain.entity.SyllabusUnit;
import constsw.grupoum.courses.domain.entity.UnitTopic;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
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

    CourseSyllabusMongo toMongoCourseSyllabus(CourseSyllabus courseSyllabus);

    CourseSyllabus toCourseSyllabus(CourseSyllabusMongo courseSyllabus);

    SyllabusUnitMongo toMongoSyallabusUnit(SyllabusUnit syllabusUnit);

    SyllabusUnit toSyllabusUnit(SyllabusUnitMongo syllabusUnit);

    UnitTopicMongo toMongoUnitTopic(UnitTopic unitTopic);

    UnitTopic toUnitTopic(UnitTopicMongo unitTopic);

}
